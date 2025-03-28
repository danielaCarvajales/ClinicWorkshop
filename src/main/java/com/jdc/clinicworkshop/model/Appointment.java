package com.jdc.clinicworkshop.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serial;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "Appointments")
@Data
public class Appointment {
    //Id of Serialization
    @Serial
    private static final long serialVersionUID = 1L;

    // Mapping of primary key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_appointment")
    private long idAppointment;

    // Mapeo de campos
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "title")
    private String title;

    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "note")
    private String note;

    @NotNull
    @Column(name = "date_appointment")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date dateAppointment;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "symptoms")
    private String symptoms;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "medications")
    private String medications;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "type_appointment")
    private String typeAppointment;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "history")
    private String history;

    //Mapping of Foreign Key
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_doctor", referencedColumnName = "id_doctor", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Doctor doctor;

    //Get Foreign Key
    @JsonProperty("fk_doctor")
    public Long getDoctorId() {
        return doctor != null ?
                doctor.getIdDoctor() : null;
    }

    //Foreign Key
    @OneToMany(mappedBy = "appointment", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<AppointmentPatient> appointmentPatientList;

}
