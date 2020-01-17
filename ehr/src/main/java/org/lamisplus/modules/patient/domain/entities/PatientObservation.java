package org.lamisplus.modules.patient.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "patient_observation")
@Data
@EqualsAndHashCode
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PatientObservation  extends JsonBEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    @Basic(fetch = FetchType.LAZY)
    private Object formData = new ArrayList<>();

    @NotNull
    private String formCode;


    @JoinColumn(name = "patient_encounter_id")
    @ManyToOne
    @JsonIgnore
    private PatientEncounter encounter;

    @JoinColumn(name = "patient_id")
    @ManyToOne
    @JsonIgnore
    private Patient patient;

    @JoinColumn(name = "service_id")
    @ManyToOne
    @JsonIgnore
    private Service service;

}
