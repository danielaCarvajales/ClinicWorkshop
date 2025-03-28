package com.jdc.clinicworkshop.repository;

import com.jdc.clinicworkshop.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryPatient extends JpaRepository<Patient, Long> {
}
