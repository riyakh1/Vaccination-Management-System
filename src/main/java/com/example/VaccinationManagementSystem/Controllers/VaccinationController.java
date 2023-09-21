package com.example.VaccinationManagementSystem.Controllers;

import com.example.VaccinationManagementSystem.Models.VaccinationCenter;
import com.example.VaccinationManagementSystem.Services.VaccinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vaccinationCenter")
public class VaccinationController {

    @Autowired
    VaccinationService vaccinationService;

    @PostMapping("/add")
    public ResponseEntity<String> addVaccCenter(@RequestBody VaccinationCenter vaccinationCenter){
        try{
            String response = vaccinationService.addVaccCenter(vaccinationCenter);
            return new ResponseEntity<>(response , HttpStatus.OK) ;
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
