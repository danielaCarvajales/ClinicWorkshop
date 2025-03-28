package com.jdc.clinicworkshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;


import java.util.Date;

@AllArgsConstructor
@Data
public class CreateAppointmentDTO {
    private String title;
    private String note;
    private Date dateAppointment;
    private String symptoms;
    private String medications;
    private String typeAppointment;
    private String history;
    private Long fk_doctor;
}
