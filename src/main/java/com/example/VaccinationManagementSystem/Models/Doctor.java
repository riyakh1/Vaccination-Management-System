package com.example.VaccinationManagementSystem.Models;

import com.example.VaccinationManagementSystem.Enums.Gender;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "doctors")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int docId;

    private String name;

    private int age;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(unique = true)
    private String emailId;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private VaccinationCenter vaccinationCenter;

    @JsonIgnore
    @OneToMany(mappedBy = "doctor" , cascade = CascadeType.ALL)
    private List<Appointment> appointmentList = new ArrayList<>();
}
