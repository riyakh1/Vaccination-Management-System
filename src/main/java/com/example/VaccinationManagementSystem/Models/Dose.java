package com.example.VaccinationManagementSystem.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Table(name = "dose")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Dose {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; //Primary Key

    @Column(unique = true)
    private String doseId; //Unique Key

    @CreationTimestamp //whenever there'll be entry in db, the time of the entry will automatically be added(only need to enter doseId)
    private Date vaccinationDate;

    @OneToOne
    @JoinColumn
    @JsonIgnore
    private User user; //Foreign Key
}
