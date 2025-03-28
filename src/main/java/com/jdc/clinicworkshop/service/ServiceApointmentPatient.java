package com.jdc.clinicworkshop.service;

import com.jdc.clinicworkshop.dto.CreateAppointmetPatientDTO;
import com.jdc.clinicworkshop.dto.CreatePatientDTO;
import com.jdc.clinicworkshop.model.AppointmentPatient;
import com.jdc.clinicworkshop.model.Patient;

import java.util.List;

public interface ServiceApointmentPatient {
    public List<AppointmentPatient> findAll(); // obtain all the  registered AppointmentPatient
    public AppointmentPatient findById(Long id); // Obtains the AppointmentPatient by id
    public void save(AppointmentPatient appointmentPatient); // Create a AppointmentPatient
    public AppointmentPatient create(CreateAppointmetPatientDTO createAppointmetPatientDTO);
    public void deleteById(Long id); // Delete AppointmentPatient by id

}
