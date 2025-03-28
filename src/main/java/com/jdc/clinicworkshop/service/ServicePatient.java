package com.jdc.clinicworkshop.service;



import com.jdc.clinicworkshop.dto.CreatePatientDTO;
import com.jdc.clinicworkshop.model.Patient;

import java.util.List;

public interface ServicePatient {
    public List<Patient> findAll(); // obtain all the  registered patient
    public Patient findById(Long id); // Obtains the patient by id
    public void save(Patient patient); // Create a patient
    public Patient create(CreatePatientDTO createPatientDTO);
    public void deleteById(Long id); //Delete by id
}
