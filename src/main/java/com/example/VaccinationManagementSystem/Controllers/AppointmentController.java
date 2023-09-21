package com.example.VaccinationManagementSystem.Controllers;

import com.example.VaccinationManagementSystem.Dtos.RequestDtos.AppointmentRequestDto;
import com.example.VaccinationManagementSystem.Services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    AppointmentService appointmentService;

    @PostMapping("/book")
    public ResponseEntity<String> bookAppointment(@RequestBody AppointmentRequestDto appointmentRequestDto){
        try{
            String result = appointmentService.bookAppointment(appointmentRequestDto);
            return new ResponseEntity<>(result , HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage() , HttpStatus.NOT_FOUND);
        }
    }
}
