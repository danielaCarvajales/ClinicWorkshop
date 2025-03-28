package com.jdc.clinicworkshop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serial;

@Entity
@Table(name = "Appointment_Patient")
@Data
public class AppointmentPatient {

    //Id of Serialización
    @Serial
    private static final long serialVersionUID = 1L;

    // Mapping of primary key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_appointment_patient")
    private Long idAppointmentPatient;

    //Mapping of Foreign Key
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_appointment", referencedColumnName = "id_appointment", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Appointment appointment;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_patient", referencedColumnName = "id_patient", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Patient patient;

    //Get Foreign Key
    @JsonProperty("fk_appointment")
    public Long getIdAppointment() {
        return appointment != null ?
                appointment.getIdAppointment() : null;
    }

    @JsonProperty("fk_patient")
    public Long getIdPatient() {
        return patient != null ?
                patient.getIdPatient() : null;
    }


}
