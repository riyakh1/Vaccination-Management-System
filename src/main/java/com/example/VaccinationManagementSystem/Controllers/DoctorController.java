package com.example.VaccinationManagementSystem.Controllers;

import com.example.VaccinationManagementSystem.Dtos.RequestDtos.AssociateDoctorDto;
import com.example.VaccinationManagementSystem.Models.Doctor;
import com.example.VaccinationManagementSystem.Services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    DoctorService doctorService;
    @PostMapping("/add")
    public String addDoctor(@RequestBody Doctor doctor){

        try {
            String response = doctorService.addDoctor(doctor);
            return response;
        }catch (Exception e){
            return e.getMessage();
        }

    }

    @PostMapping("associateDoctor")
    public ResponseEntity<String> associateDoctor(@RequestBody AssociateDoctorDto associateDoctorDto){
        try{
            String result = doctorService.associateDoctor(associateDoctorDto);
            return new ResponseEntity<>(result , HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    //Get the list of doctors with more than 10 appointments
    @GetMapping("/getAllTheDoctorsWithMoreThan10Appointments")
    public ResponseEntity<List<Doctor>> doctorsWithMoreThan10Appointments(){
        List<Doctor> doctorList = doctorService.doctorsWithMoreThan10Appointments();
        return new ResponseEntity<>(doctorList , HttpStatus.OK);
    }

    // get all the male doctors whose age is above 40
    @GetMapping("allMaleDoctorsAboveAge40")
    public ResponseEntity<List<Doctor>> maleDoctorsAboveAge40(){
        List<Doctor> doctorList = doctorService.maleDoctorsAboveAge40();
        return new ResponseEntity<>(doctorList , HttpStatus.OK);
    }

    // get the ratio of male to female doctors
    @GetMapping("/ratioOfMaleToFemaleDoctors")
    public ResponseEntity<Float> ratioOfMaleToFemaleDoctors(){
        return new ResponseEntity<>(doctorService.ratioOfMaleToFemaleDoctors() , HttpStatus.OK);
    }
}
