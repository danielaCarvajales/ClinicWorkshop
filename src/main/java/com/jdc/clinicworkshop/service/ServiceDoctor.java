package com.jdc.clinicworkshop.service;



import com.jdc.clinicworkshop.dto.CreateDoctorDTO;
import com.jdc.clinicworkshop.model.Doctor;

import java.util.List;

public interface ServiceDoctor {
    public List<Doctor> findAll(); // Obtain all registers from a appointment
    public Doctor findById(Long id); // Obtain a doctor's registration by ID
    public Doctor save(CreateDoctorDTO doctorDTO); // Create a doctor
    Doctor saves(Doctor doctor);
    public void deleteById(Long id); //Delete for id
}
