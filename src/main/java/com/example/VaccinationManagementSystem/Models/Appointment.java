package com.example.VaccinationManagementSystem.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.Date;

@Entity
@Table(name = "appointments")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Date appointmentDate;

    private LocalTime appointmentTime;


    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private Doctor doctor;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private User user;
}
