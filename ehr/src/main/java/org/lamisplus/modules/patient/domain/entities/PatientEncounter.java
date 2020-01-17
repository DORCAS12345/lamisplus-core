package org.lamisplus.modules.patient.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "patient_encounter")
@Data
@EqualsAndHashCode
public class PatientEncounter implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;

    @JoinColumn(name = "class_")
    @ManyToOne
    private Codifier class_;

    @Basic
    @Column(name = "date_encounter")
    private LocalDate dateEncounter;


    @JoinColumn(name = "services_id")
    @ManyToOne
    private Service service;

    @JoinColumn(name = "patient_id")
    @ManyToOne
    @NotNull
    private Patient patient;


    @ManyToOne
    @JoinColumn(name = "part_of_id")
    private PatientEncounter partOf;

    @ManyToOne
    private Facility location;

    @ManyToMany
    @JsonIgnore
    private List<Person> participants;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "encounter")
    @JsonIgnore
    private List<PatientObservation> PatientObservation;

}
