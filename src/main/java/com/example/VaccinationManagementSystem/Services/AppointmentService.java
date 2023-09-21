package com.example.VaccinationManagementSystem.Services;

import com.example.VaccinationManagementSystem.Dtos.RequestDtos.AppointmentRequestDto;
import com.example.VaccinationManagementSystem.Exceptions.DoctorNotFoundException;
import com.example.VaccinationManagementSystem.Exceptions.UserNotFoundException;
import com.example.VaccinationManagementSystem.Models.Appointment;
import com.example.VaccinationManagementSystem.Models.Doctor;
import com.example.VaccinationManagementSystem.Models.User;
import com.example.VaccinationManagementSystem.Repository.AppointmentRepository;
import com.example.VaccinationManagementSystem.Repository.DoctorRepository;
import com.example.VaccinationManagementSystem.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppointmentService {
    @Autowired
    AppointmentRepository appointmentRepository;

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private JavaMailSender emailSender;

    public String bookAppointment(AppointmentRequestDto appointmentRequestDto) throws DoctorNotFoundException , UserNotFoundException {
        Integer docId = appointmentRequestDto.getDocId();
        Optional<Doctor> doctorOptional = doctorRepository.findById(docId);

        //Validation
        if(doctorOptional.isEmpty()){
            throw new DoctorNotFoundException("Doctor id is wrong");
        }

        Integer userId = appointmentRequestDto.getUserId();
        Optional<User> userOptional = userRepository.findById(userId);

        //Validation
        if(userOptional.isEmpty()){
            throw new UserNotFoundException("User id is wrong");
        }
        //Get the entity from optional
        Doctor doctor = doctorOptional.get();
        User user = userOptional.get();

        //Creating object and setting its attributes
        Appointment appointment = new Appointment();

        appointment.setAppointmentDate(appointmentRequestDto.getAppointmentDate());
        appointment.setAppointmentTime(appointmentRequestDto.getAppointmentTime());
        //Setting the foreign key in child entity
        appointment.setDoctor(doctor);
        appointment.setUser(user);

        //save appointment to avoid duplicate saving
        appointment = appointmentRepository.save(appointment);

        //Set the bi-directional mapping
        doctor.getAppointmentList().add(appointment);
        user.getAppointmentList().add(appointment);

        //Save the parent
        doctorRepository.save(doctor);
        userRepository.save(user);


        //Send email to the sender after booking the appointment
        String body = "Hi!"+user.getName()+"\n"+
                "You have successfully booked an appointment on" + appointment.getAppointmentDate() + "at time" + appointment.getAppointmentTime() + "\n"+
                "Your doctor is" + appointment.getDoctor().getName() +"\n"+
                "Please reach at " + doctor.getVaccinationCenter().getAddress();

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        //set its attributes
        mailMessage.setFrom("dummymail1240@gmail.com");
        mailMessage.setTo(user.getEmailId());
        mailMessage.setSubject("Appointment Confirmed !!");
        mailMessage.setText(body);

        emailSender.send(mailMessage);

        return "Appointment has been booked successfully";
    }
}
