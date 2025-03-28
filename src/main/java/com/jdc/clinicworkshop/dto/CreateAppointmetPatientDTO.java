package com.jdc.clinicworkshop.dto;

import com.jdc.clinicworkshop.model.Appointment;
import com.jdc.clinicworkshop.model.Patient;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateAppointmetPatientDTO {
    private Long idAppointmentPatient;
    private Appointment appointment;
    private Patient patient;

}
