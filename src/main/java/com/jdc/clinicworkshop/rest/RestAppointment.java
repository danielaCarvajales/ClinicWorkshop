package com.jdc.clinicworkshop.rest;


import com.jdc.clinicworkshop.dto.CreateAppointmentDTO;
import com.jdc.clinicworkshop.dto.UpdatAppointmentDTO;
import com.jdc.clinicworkshop.model.Appointment;
import com.jdc.clinicworkshop.model.Doctor;
import com.jdc.clinicworkshop.repository.RepositoryDoctor;
import com.jdc.clinicworkshop.service.ServiceAppointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointment")
public class RestAppointment {

    @Autowired
    private ServiceAppointment serviceAppointment;

    @Autowired
    private RepositoryDoctor doctorRepository;

    // Endpoint to get all the appointment
    @GetMapping("/list")
    private ResponseEntity<List<Appointment>> list(){
        return  ResponseEntity.ok(serviceAppointment.findAll());
    }

    // Endpoint to gets Appointments by id
    @GetMapping("/findby/{id}")
    private ResponseEntity<Appointment> listIndividual(@PathVariable("id") Long id){
        return ResponseEntity.ok(serviceAppointment.findById(id));
    }

    //Enpoint to Create Appointment
    @PostMapping("/add")
    public ResponseEntity<Appointment> create(@RequestBody CreateAppointmentDTO appointmentDTO) {
        try {
            // Convert DTO to entity
            Appointment appointment = new Appointment();
            appointment.setTitle(appointmentDTO.getTitle());
            appointment.setNote(appointmentDTO.getNote());
            appointment.setDateAppointment(appointmentDTO.getDateAppointment());
            appointment.setSymptoms(appointmentDTO.getSymptoms());
            appointment.setMedications(appointmentDTO.getMedications());
            appointment.setTypeAppointment(appointmentDTO.getTypeAppointment());
            appointment.setHistory(appointmentDTO.getHistory());

            Doctor doctor = doctorRepository.findById(appointmentDTO.getFk_doctor())
                    .orElseThrow(() -> new RuntimeException("Doctor no encontrado"));
            appointment.setDoctor(doctor);

            serviceAppointment.save(appointment);

            return ResponseEntity.ok(appointment);
        } catch (Exception e) {
            System.out.println("El error es: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    //Endpoint to delete by id one appointment
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        try {
            Appointment appointment = serviceAppointment.findById(id);
            if (appointment == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("La cita con ID " + id + " no existe");
            }
            serviceAppointment.deleteById(id);
            return ResponseEntity.ok(list());
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("No se puede eliminar la cita porque está siendo utilizada por otros registros. " );
        } catch (Exception e) {
            System.out.println("El error es: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al eliminar la cita: " + e.getMessage());
        }
    }

    //Enpoint to update Appointment
    @PutMapping("/update/{id}")
    public ResponseEntity<Appointment> editar(
                                              @Validated
                                              @RequestBody UpdatAppointmentDTO appointmentRequest,
                                              @PathVariable("id") Long id) {
        Appointment appointmentExist = serviceAppointment.findById(id);
            appointmentExist.setNote(appointmentRequest.getNote());
            appointmentExist.setHistory(appointmentRequest.getHistory());
            appointmentExist.setSymptoms(appointmentRequest.getSymptoms());
        serviceAppointment.save(appointmentExist);
        return ResponseEntity.ok(appointmentExist);
    }

}
