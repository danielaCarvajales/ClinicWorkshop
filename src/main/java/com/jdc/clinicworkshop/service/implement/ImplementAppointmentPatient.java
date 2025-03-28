package com.jdc.clinicworkshop.service.implement;

import com.jdc.clinicworkshop.dto.CreateAppointmetPatientDTO;
import com.jdc.clinicworkshop.model.AppointmentPatient;
import com.jdc.clinicworkshop.model.Doctor;
import com.jdc.clinicworkshop.model.Patient;
import com.jdc.clinicworkshop.repository.RepositoryAppointmentPatient;
import com.jdc.clinicworkshop.service.ServiceApointmentPatient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImplementAppointmentPatient implements ServiceApointmentPatient {

    @Autowired
    private RepositoryAppointmentPatient repositoryAppointmentPatient;

    @Override
    public List<AppointmentPatient> findAll() {
        return repositoryAppointmentPatient.findAll();
    }

    @Override
    public AppointmentPatient findById(Long id) {
        return repositoryAppointmentPatient.findById(id).orElse(null);
    }

    @Override
    public void save(AppointmentPatient appointmentPatient) {
        repositoryAppointmentPatient.save(appointmentPatient);
    }

    @Override
    public AppointmentPatient create(CreateAppointmetPatientDTO createAppointmetPatientDTO) {
        AppointmentPatient appointmentPatient = new AppointmentPatient();
        appointmentPatient.setAppointment(createAppointmetPatientDTO.getAppointment());
        appointmentPatient.setPatient(createAppointmetPatientDTO.getPatient());
        return repositoryAppointmentPatient.save(appointmentPatient);
    }

    @Override
    public void deleteById(Long id) {
        repositoryAppointmentPatient.deleteById(id);
    }
}
