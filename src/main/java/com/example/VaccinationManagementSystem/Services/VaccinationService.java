package com.example.VaccinationManagementSystem.Services;

import com.example.VaccinationManagementSystem.Enums.Gender;
import com.example.VaccinationManagementSystem.Exceptions.VaccinationCenterAddressNotFound;
import com.example.VaccinationManagementSystem.Exceptions.VaccinationCenterNotFoundException;
import com.example.VaccinationManagementSystem.Models.Doctor;
import com.example.VaccinationManagementSystem.Models.VaccinationCenter;
import com.example.VaccinationManagementSystem.Repository.VaccinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VaccinationService {
    @Autowired
    VaccinationRepository vaccinationRepository;

    public String addVaccCenter(VaccinationCenter vaccinationCenter) throws VaccinationCenterAddressNotFound{
        if(vaccinationCenter.getAddress() == null){
            throw new VaccinationCenterAddressNotFound("Vaccination Center Address is Empty");
        }

        vaccinationRepository.save(vaccinationCenter);
        return "Vaccination Center added at a location " + vaccinationCenter.getAddress();
    }

    public List<Doctor> doctorsAtAParticularCenter(int centerId)throws VaccinationCenterNotFoundException {
        Optional<VaccinationCenter> vaccinationCenterOptional = vaccinationRepository.findById(centerId);

        //Validation check
        if(vaccinationCenterOptional.isEmpty()){
            throw new VaccinationCenterNotFoundException("Center id is incorrect");
        }

        VaccinationCenter vaccinationCenter = vaccinationCenterOptional.get();
        List<Doctor> doctorList = vaccinationCenter.getDoctorList();

        return doctorList;
    }

    public List<Doctor> listOfAllMaleDoctors(int centerId)throws VaccinationCenterNotFoundException {
        Optional<VaccinationCenter> vaccinationCenterOptional = vaccinationRepository.findById(centerId);

        //Validation check
        if(vaccinationCenterOptional.isEmpty()){
            throw new VaccinationCenterNotFoundException("Center id is incorrect");
        }

        VaccinationCenter vaccinationCenter = vaccinationCenterOptional.get();
        List<Doctor> doctorList = vaccinationCenter.getDoctorList();

        List<Doctor> maleDoctorList = new ArrayList<>();
        //Checking for male doctors
        for(Doctor doctor : doctorList){
            if(doctor.getGender().equals(Gender.MALE))
                maleDoctorList.add(doctor);
        }
        return maleDoctorList;
    }

    public List<Doctor> listOfAllFemaleDoctors(int centerId)throws VaccinationCenterNotFoundException {
        Optional<VaccinationCenter> vaccinationCenterOptional = vaccinationRepository.findById(centerId);

        //Validation check
        if(vaccinationCenterOptional.isEmpty()){
            throw new VaccinationCenterNotFoundException("Center id id incorrect");
        }

        VaccinationCenter vaccinationCenter = vaccinationCenterOptional.get();
        List<Doctor> doctorList = vaccinationCenter.getDoctorList();

        List<Doctor> femaleDoctors = new ArrayList<>();

        for(Doctor doctor : doctorList){
            if(doctor.getGender().equals(Gender.FEMALE)) femaleDoctors.add(doctor);
        }

        return femaleDoctors;
    }
}
