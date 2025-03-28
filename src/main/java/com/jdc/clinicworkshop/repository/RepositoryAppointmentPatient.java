package com.jdc.clinicworkshop.repository;

import com.jdc.clinicworkshop.model.AppointmentPatient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryAppointmentPatient extends JpaRepository<AppointmentPatient, Long> {
}
