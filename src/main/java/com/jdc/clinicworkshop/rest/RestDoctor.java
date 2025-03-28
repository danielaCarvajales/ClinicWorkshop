package com.jdc.clinicworkshop.rest;


import com.jdc.clinicworkshop.dto.CreateDoctorDTO;
import com.jdc.clinicworkshop.dto.UpdateDoctorDTO;
import com.jdc.clinicworkshop.model.Doctor;
import com.jdc.clinicworkshop.repository.RepositoryDoctor;
import com.jdc.clinicworkshop.service.ServiceDoctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctors")
public class RestDoctor {
    @Autowired
    private ServiceDoctor serviceDoctor;

    @Autowired
    private RepositoryDoctor doctorRepository;

    // Endpoint to get all the doctor
    @GetMapping("/list")
    private ResponseEntity<List<Doctor>> list(){
        return  ResponseEntity.ok(serviceDoctor.findAll());
    }

    // Endpoint to gets doctor by id
    @GetMapping("/findby/{id}")
    private ResponseEntity<Doctor> listIndividual(@PathVariable("id") Long id){
        return ResponseEntity.ok(serviceDoctor.findById(id));
    }

    //Enpoint to Create doctor
    @PostMapping("/add")
    public ResponseEntity<Doctor> create(@RequestBody CreateDoctorDTO doctorDTO){
        try {
            Doctor savedDoctor = serviceDoctor.save(doctorDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedDoctor);
        } catch (Exception e) {
            System.out.println("El error es: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    //Endpoint to delete by id one doctor
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        try {
            Doctor doctor = serviceDoctor.findById(id);
            if (doctor == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("El doctor con id " + id + " no existe");
            }
            serviceDoctor.deleteById(id);
            return ResponseEntity.ok(list());
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("No se puede eliminar el doctor porque está siendo utilizada por otros registros. " );
        } catch (Exception e) {
            System.out.println("El error es: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al eliminar doctor: " + e.getMessage());
        }
    }

    //Enpoint to update doctor
    @PutMapping("/update/{id}")
    public ResponseEntity<Doctor> editar(
            @Validated
            @RequestBody UpdateDoctorDTO updateDoctorDTO,
            @PathVariable("id") Long id) {
        Doctor doctorExist = serviceDoctor.findById(id);
        doctorExist.setEmail(updateDoctorDTO.getEmail());
        doctorExist.setPhone(updateDoctorDTO.getPhone());
        serviceDoctor.saves(doctorExist);
        return ResponseEntity.ok(doctorExist);
    }

}


