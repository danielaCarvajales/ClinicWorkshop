package com.jdc.clinicworkshop.repository;

import com.jdc.clinicworkshop.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryAppointment  extends JpaRepository<Appointment, Long> {

}
