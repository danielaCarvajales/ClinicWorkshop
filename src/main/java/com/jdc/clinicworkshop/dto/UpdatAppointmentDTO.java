package com.jdc.clinicworkshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdatAppointmentDTO {
    private String note;
    private String history;
    private String symptoms;
}
