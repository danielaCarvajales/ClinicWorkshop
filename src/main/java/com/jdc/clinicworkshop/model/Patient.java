package com.jdc.clinicworkshop.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serial;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "patients")
@Data
public class Patient {
    //Id of Serialización
    @Serial
    private static final long serialVersionUID = 1L;

    // Mapping of primary key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_patient")
    private Long idPatient;

    // Mapeo de campos
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "names")
    private String names;

    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "first_last_name")
    private String firstLastName;

    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "second_last_name")
    private String secondLastName;

    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "document")
    private String document;

    @NotNull
    @Column(name = "birthdate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date birthdate;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "medications")
    private String medications;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "allergies")
    private String allergies;

    //Foreign Key
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<AppointmentPatient> appointmentPatientList;
}
