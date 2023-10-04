package com.example.VaccinationManagementSystem.Controllers;

import com.example.VaccinationManagementSystem.Models.Doctor;
import com.example.VaccinationManagementSystem.Models.VaccinationCenter;
import com.example.VaccinationManagementSystem.Services.VaccinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    // give the list of all doctors at a particular center(centerId)
    @GetMapping("listOfDoctorsAtAParticularVaccinationCenter")
    public ResponseEntity<?> doctorsAtAParticularCenter(@RequestParam int centerId){
        try{
            return new ResponseEntity<>(vaccinationService.doctorsAtAParticularCenter(centerId) , HttpStatus.FOUND);
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage() , HttpStatus.NOT_FOUND);
        }
    }

    // give the list of all male doctors at a particular center(centerId)
    @GetMapping("listOfAllMaleDoctorsInACenter")
    public ResponseEntity<?> listOfAllMaleDoctors(@RequestParam int centerId){
        try{
            return new ResponseEntity<>(vaccinationService.listOfAllMaleDoctors(centerId) , HttpStatus.FOUND);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage() , HttpStatus.NOT_FOUND);
        }
    }

    // give the list of all females doctors at a particular center(centerId)
    @GetMapping("listOfAllFemaleDoctorsInACenter")
    public ResponseEntity<?> listOfAllFemaleDoctorsInACenter(@RequestParam int centerId){
        try{
            return new ResponseEntity<>(vaccinationService.listOfAllFemaleDoctors(centerId) , HttpStatus.FOUND);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage() , HttpStatus.NOT_FOUND);
        }
    }
}
