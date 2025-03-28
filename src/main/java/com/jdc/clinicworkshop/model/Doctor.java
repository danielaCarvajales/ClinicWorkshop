package com.jdc.clinicworkshop.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serial;
import java.util.List;


@Entity
@Table(name = "Doctors")
@Data
public class Doctor {

    //Id of Serialización
    @Serial
    private static final long serialVersionUID = 1L;

    // Mapping of primary key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id_doctor")
    private Long idDoctor;

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
    @Column(name = "gender")
    private Integer gender;


    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "email")
    private String email;

    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "phone")
    private String phone;

    //Foreign Key
    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Appointment> appointments;


}

