package com.example.VaccinationManagementSystem.Services;

import com.example.VaccinationManagementSystem.Dtos.RequestDtos.AssociateDoctorDto;
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
}
