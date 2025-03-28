package com.jdc.clinicworkshop.repository;

import com.jdc.clinicworkshop.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryDoctor extends JpaRepository<Doctor, Long> {

}
