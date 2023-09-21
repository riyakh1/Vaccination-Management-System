package com.example.VaccinationManagementSystem.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "vaccination_center")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VaccinationCenter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String centerName;
    private LocalTime openingTime;
    private LocalTime closingTime;

    private String address;
    private int doseCapacity;

    @JsonIgnore
    @OneToMany(mappedBy = "vaccinationCenter" , cascade = CascadeType.ALL)
    private List<Doctor> doctorList = new ArrayList<>();
 }
