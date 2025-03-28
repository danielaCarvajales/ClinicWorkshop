package com.jdc.clinicworkshop.rest;

import com.jdc.clinicworkshop.dto.*;
import com.jdc.clinicworkshop.model.Patient;
import com.jdc.clinicworkshop.repository.RepositoryPatient;
import com.jdc.clinicworkshop.service.ServicePatient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/patients")
public class RestPatient {


    @Autowired
    private ServicePatient servicePatient;

    @Autowired
    private RepositoryPatient repositoryPatient;

    // Endpoint to get all the Patient
    @GetMapping("/list")
    private ResponseEntity<List<Patient>> list(){
        return  ResponseEntity.ok(servicePatient.findAll());
    }

    // Endpoint to gets Patients by id
    @GetMapping("/findby/{id}")
    private ResponseEntity<Patient> listIndividual(@PathVariable("id") Long id){
        return ResponseEntity.ok(servicePatient.findById(id));
    }

    //Enpoint to Create Patient
    @PostMapping("/add")
    public ResponseEntity<Patient> create(@RequestBody CreatePatientDTO patientDTO){
        try {
            Patient savedPatient = servicePatient.create(patientDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedPatient);
        } catch (Exception e) {
            System.out.println("El error es: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    //Endpoint to delete by id one Patient
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        try {
            Patient patient = servicePatient.findById(id);
            if (patient == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("El paciente con ID " + id + " no existe");
            }
            servicePatient.deleteById(id);
            return ResponseEntity.ok(list());
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("No se puede eliminar el paciente porque está siendo utilizada por otros registros. " );
        } catch (Exception e) {
            System.out.println("El error es: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al eliminar el paciente: " + e.getMessage());
        }
    }

    //Enpoint to update Patient
    @PutMapping("/update/{id}")
    public ResponseEntity<Patient> editar(
            @Validated
            @RequestBody UpdatePatientsDTO patientsDTO,
            @PathVariable("id") Long id) {
        Patient patient = servicePatient.findById(id);
        patient.setMedications(patientsDTO.getMedications());
        patient.setAllergies(patientsDTO.getAllergies());
        servicePatient.save(patient);
        return ResponseEntity.ok(patient);
    }

}
