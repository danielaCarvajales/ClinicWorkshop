package com.jdc.clinicworkshop.service;

import com.jdc.clinicworkshop.model.Appointment;

import java.util.List;

public interface ServiceAppointment {
    public List<Appointment> findAll(); // obtain all the  registered appointments
    public Appointment findById(Long id); // Obtains the Appointment by id
    public void save(Appointment appointment); // Create a appointments
    public void deleteById(Long id); //Delete by id

}
