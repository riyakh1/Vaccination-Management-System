package com.example.VaccinationManagementSystem.Dtos.RequestDtos;

import lombok.Data;

import java.time.LocalTime;
import java.util.Date;

@Data
public class AppointmentRequestDto {
    private int docId;
    private int userId;
    private Date appointmentDate;
    private LocalTime appointmentTime;
}
