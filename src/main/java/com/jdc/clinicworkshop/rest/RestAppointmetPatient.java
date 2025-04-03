package com.jdc.clinicworkshop.rest;


import com.jdc.clinicworkshop.dto.CreateAppointmetPatientDTO;
import com.jdc.clinicworkshop.model.Appointment;
import com.jdc.clinicworkshop.model.AppointmentPatient;
import com.jdc.clinicworkshop.model.Patient;
import com.jdc.clinicworkshop.repository.RepositoryAppointment;
import com.jdc.clinicworkshop.repository.RepositoryAppointmentPatient;
import com.jdc.clinicworkshop.repository.RepositoryPatient;
import com.jdc.clinicworkshop.service.ServiceApointmentPatient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/appointment_patient")
public class RestAppointmetPatient {
    @Autowired
    private ServiceApointmentPatient serviceApointmentPatient;
    @Autowired
    private RepositoryAppointment appointmentRepository;

    @Autowired
    private RepositoryPatient patientRepository;

    @Autowired
    private RepositoryAppointmentPatient repositoryAppointmentPatient;

    // Endpoint to get all the  Appointme patient
    @GetMapping("/list")
    private ResponseEntity<List<AppointmentPatient>> list(){
        return  ResponseEntity.ok(serviceApointmentPatient.findAll());
    }

    // Endpoint to gets  Appointme patient by id
    @GetMapping("/findby/{id}")
    private ResponseEntity<AppointmentPatient> listIndividual(@PathVariable("id") Long id){
        return ResponseEntity.ok(serviceApointmentPatient.findById(id));
    }

    //Enpoint to Create  Appointme patient
    @PostMapping("/add")
    public ResponseEntity<AppointmentPatient> create(@RequestBody Map<String, Object> requestMap) {
        try {
            Long appointmentId = Long.valueOf(requestMap.get("fk_appointment").toString());
            Long patientId = Long.valueOf(requestMap.get("fk_patient").toString());
            Appointment appointment = appointmentRepository.findById(appointmentId)
                    .orElseThrow(() -> new RuntimeException("Cita no encontrada con ID: " + appointmentId));
            Patient patient = patientRepository.findById(patientId)
                    .orElseThrow(() -> new RuntimeException("Paciente no encontrado con ID: " + patientId));

            CreateAppointmetPatientDTO dto = new CreateAppointmetPatientDTO(null, appointment, patient);

            AppointmentPatient savedEntity = serviceApointmentPatient.create(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedEntity);
        } catch (Exception e) {
            System.out.println("El error es: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    //Endpoint to delete by id one  Appointme patient
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        try {
            AppointmentPatient appointmentPatient = serviceApointmentPatient.findById(id);
            if (appointmentPatient == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("La cita paciente con id " + id + " no existe");
            }
            serviceApointmentPatient.deleteById(id);
            return ResponseEntity.ok(list());
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("No se puede eliminar la cita paciente porque está siendo utilizada por otros registros. " );
        } catch (Exception e) {
            System.out.println("El error es: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al eliminar cita paciente: " + e.getMessage());
        }
    }

    //Enpoint to update Appointme patient
    @PutMapping("/update/{id}")
    public ResponseEntity<AppointmentPatient> editar(@RequestBody Map<String, Object> requestMap,
                                                     @PathVariable("id") Long id) {
        try {
            AppointmentPatient appointmentPatient = serviceApointmentPatient.findById(id);
            if (appointmentPatient == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            Long appointmentId = Long.valueOf(requestMap.get("fk_appointment").toString());
            Long patientId = Long.valueOf(requestMap.get("fk_patient").toString());

            Appointment appointment = appointmentRepository.findById(appointmentId)
                    .orElseThrow(() -> new RuntimeException("Cita no encontrada con ID: " + appointmentId));
            Patient patient = patientRepository.findById(patientId)
                    .orElseThrow(() -> new RuntimeException("Paciente no encontrado con ID: " + patientId));

            appointmentPatient.setAppointment(appointment);
            appointmentPatient.setPatient(patient);

            serviceApointmentPatient.save(appointmentPatient);
            return ResponseEntity.ok(appointmentPatient);
        } catch (Exception e) {
            System.out.println("El error es: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
