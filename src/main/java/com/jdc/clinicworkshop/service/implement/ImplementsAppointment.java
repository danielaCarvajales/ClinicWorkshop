package com.jdc.clinicworkshop.service.implement;

import com.jdc.clinicworkshop.model.Appointment;
import com.jdc.clinicworkshop.repository.RepositoryAppointment;
import com.jdc.clinicworkshop.service.ServiceAppointment;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ImplementsAppointment implements ServiceAppointment {

    @Autowired
    private RepositoryAppointment repositoryAppointment;

    @Override
    @Transactional
    public List<Appointment> findAll() {
        return repositoryAppointment.findAll();
    }

    @Override
    @Transactional
    public Appointment findById(Long id) {
        return repositoryAppointment.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void save(Appointment appointment) {
        repositoryAppointment.save(appointment);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        repositoryAppointment.deleteById(id);
    }


}
