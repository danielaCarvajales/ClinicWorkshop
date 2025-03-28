package com.jdc.clinicworkshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdatePatientsDTO {
    private String medications;
    private String allergies;
}
