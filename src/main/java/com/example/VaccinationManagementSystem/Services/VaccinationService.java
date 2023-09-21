package com.example.VaccinationManagementSystem.Services;

import com.example.VaccinationManagementSystem.Exceptions.VaccinationCenterAddressNotFound;
import com.example.VaccinationManagementSystem.Models.VaccinationCenter;
import com.example.VaccinationManagementSystem.Repository.VaccinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
