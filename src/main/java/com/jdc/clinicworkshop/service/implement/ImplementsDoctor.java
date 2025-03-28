package com.jdc.clinicworkshop.service.implement;

import com.jdc.clinicworkshop.dto.CreateDoctorDTO;
import com.jdc.clinicworkshop.model.Doctor;
import com.jdc.clinicworkshop.repository.RepositoryDoctor;
import com.jdc.clinicworkshop.service.ServiceDoctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImplementsDoctor implements ServiceDoctor {

    @Autowired
    private RepositoryDoctor repositoryDoctor;

    @Override
    public List<Doctor> findAll() {
        return repositoryDoctor.findAll();
    }

    @Override
    public Doctor findById(Long id) {
        return repositoryDoctor.findById(id).orElse(null);
    }

    @Override
    public Doctor save(CreateDoctorDTO doctorDTO) {
        Doctor doctor = new Doctor();
        doctor.setNames(doctorDTO.getNames());
        doctor.setFirstLastName(doctorDTO.getFirstLastName());
        doctor.setSecondLastName(doctorDTO.getSecondLastName());
        doctor.setGender(doctorDTO.getGender());
        doctor.setEmail(doctorDTO.getEmail());
        doctor.setPhone(doctorDTO.getPhone());
        return repositoryDoctor.save(doctor);
    }

    @Override
    public Doctor saves(Doctor doctor) {
       return repositoryDoctor.save(doctor);
    }


    @Override
    public void deleteById(Long id) {
        repositoryDoctor.deleteById(id);
    }
}
