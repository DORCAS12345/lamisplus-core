package org.lamisplus.modules.patient.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "patient_visit")
@Data
@EqualsAndHashCode
public class PatientVisit implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "date_visit_start")
    private LocalDate dateVisitStart;

    @Column(name = "date_visit_end")
    private LocalDate dateVisitEnd;

    @Column(name = "time_visit_start")
    private LocalTime timeVisitStart;

    @Column(name = "time_visit_end")
    private LocalTime timeVisitEnd;

    @JoinColumn(name = "patient_id")
    @ManyToOne
    @JsonIgnore
    private Patient patient;

    @JoinColumn(name = "visit_type_id")
    @ManyToOne
    @JsonIgnore
    private Codifier visitTypeId;



}
