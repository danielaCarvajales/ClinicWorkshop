package com.jdc.clinicworkshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateDoctorDTO {

    private String names;
    private String firstLastName;
    private String secondLastName;
    private int gender;
    private String email;
    private String phone;
}
