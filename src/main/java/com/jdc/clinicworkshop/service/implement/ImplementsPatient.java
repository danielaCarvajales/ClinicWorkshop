package com.jdc.clinicworkshop.service.implement;

import com.jdc.clinicworkshop.dto.CreatePatientDTO;
import com.jdc.clinicworkshop.model.Appointment;
import com.jdc.clinicworkshop.model.Patient;

import com.jdc.clinicworkshop.repository.RepositoryPatient;
import com.jdc.clinicworkshop.service.ServicePatient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImplementsPatient implements ServicePatient {
    @Autowired
    private RepositoryPatient repositoryPatient;

    @Override
    public List<Patient> findAll() {
        return repositoryPatient.findAll();
    }

    @Override
    public Patient findById(Long id) {
        return repositoryPatient.findById(id).orElse(null);
    }

    @Override
    public void save(Patient patient) {
        repositoryPatient.save(patient);
    }

    @Override
    public Patient create(CreatePatientDTO createPatientDTO) {
        Patient patient = new Patient();
        patient.setNames(createPatientDTO.getNames());
        patient.setFirstLastName(createPatientDTO.getFirstLastName());
        patient.setSecondLastName(createPatientDTO.getSecondLastName());
        patient.setDocument(createPatientDTO.getDocument());
        patient.setBirthdate(createPatientDTO.getBirthdate());
        patient.setMedications(createPatientDTO.getMedications());
        patient.setAllergies(createPatientDTO.getAllergies());
        return repositoryPatient.save(patient);
    }

    @Override
    public void deleteById(Long id) {
         repositoryPatient.deleteById(id);
    }
}
