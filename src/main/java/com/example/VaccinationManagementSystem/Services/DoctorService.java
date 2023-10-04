package com.example.VaccinationManagementSystem.Services;

import com.example.VaccinationManagementSystem.Dtos.RequestDtos.AssociateDoctorDto;
import com.example.VaccinationManagementSystem.Enums.Gender;
import com.example.VaccinationManagementSystem.Exceptions.DoctorNotFoundException;
import com.example.VaccinationManagementSystem.Exceptions.EmailIdEmptyException;
import com.example.VaccinationManagementSystem.Exceptions.DoctorAlreadyExistsException;
import com.example.VaccinationManagementSystem.Exceptions.VaccinationCenterNotFoundException;
import com.example.VaccinationManagementSystem.Models.Doctor;
import com.example.VaccinationManagementSystem.Models.VaccinationCenter;
import com.example.VaccinationManagementSystem.Repository.DoctorRepository;
import com.example.VaccinationManagementSystem.Repository.VaccinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    VaccinationRepository vaccinationRepository;
    public String addDoctor(Doctor doctor)throws EmailIdEmptyException, DoctorAlreadyExistsException {

        //Validation
        if(doctor.getEmailId() == null){
            throw new EmailIdEmptyException("Email id is mandatory");
        }

        if(doctorRepository.findByEmailId(doctor.getEmailId())!=null){
            throw new DoctorAlreadyExistsException("Doctor with this emailId already exits.");
        }

        //in case emailId is new
        doctorRepository.save(doctor);

        return "Doctor has been added to the database";
    }

    public String associateDoctor(AssociateDoctorDto associateDoctorDto) throws DoctorNotFoundException, VaccinationCenterNotFoundException {
        Integer docId = associateDoctorDto.getDocId();
        Optional<Doctor> doctorOptional = doctorRepository.findById(docId);
        //Validation
        if(doctorOptional.isEmpty()){
            throw new DoctorNotFoundException("Doctor id is wrong");
        }

        Integer centerId = associateDoctorDto.getCenterId();
        Optional<VaccinationCenter> vaccinationCenterOptional = vaccinationRepository.findById(centerId);
        //Validation
        if(vaccinationCenterOptional.isEmpty()){
            throw new VaccinationCenterNotFoundException("Vaccnination id is wrong");
        }

        //Get the entity from optional
        Doctor doctor = doctorOptional.get();
        VaccinationCenter vaccinationCenter = vaccinationCenterOptional.get();

        //Set the foreign Key in child entity
        doctor.setVaccinationCenter(vaccinationCenter);

        //Set the bidirectional mapping
        vaccinationCenter.getDoctorList().add(doctor); //adding doc to the list of doctors of that vacc center

        //Save the parent
        vaccinationRepository.save(vaccinationCenter);

        return "Doctor has been associated to the Vaccination Center";
    }

    public List<Doctor> doctorsWithMoreThan10Appointments() {
        List<Doctor> doctorList = doctorRepository.findAll();

        List<Doctor> doctorsWithMoreThan10Appointments = new ArrayList<>();

        for(Doctor doctor : doctorList){
            if(doctor.getAppointmentList().size() > 10) doctorsWithMoreThan10Appointments.add(doctor);
        }

        return doctorsWithMoreThan10Appointments;
    }

    public List<Doctor> maleDoctorsAboveAge40() {
        List<Doctor> doctorList = doctorRepository.findAll();
        List<Doctor> maleDoctorsAboveAge40 = new ArrayList<>();

        for(Doctor doctor : doctorList){
            if(doctor.getAge() > 40) maleDoctorsAboveAge40.add(doctor);
        }

        return maleDoctorsAboveAge40;
    }

    public float ratioOfMaleToFemaleDoctors() {
        List<Doctor> doctorList = doctorRepository.findAll();

        int maleDoctors = 0;
        int femaleDoctors = 0;

        for(Doctor doctor : doctorList){
            if(doctor.getGender().equals(Gender.MALE)) maleDoctors++;
            if(doctor.getGender().equals(Gender.FEMALE)) femaleDoctors++;
        }

        return (float) maleDoctors / femaleDoctors;
    }
}
