package com.jdc.clinicworkshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
@Data
@AllArgsConstructor
public class CreatePatientDTO {
    private String names;
    private String firstLastName;
    private String secondLastName;
    private String document;
    private Date birthdate;
    private String medications;
    private String allergies;

}
