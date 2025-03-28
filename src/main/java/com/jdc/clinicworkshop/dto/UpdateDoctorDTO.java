package com.jdc.clinicworkshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateDoctorDTO {
    private String email;
    private String phone;
}
