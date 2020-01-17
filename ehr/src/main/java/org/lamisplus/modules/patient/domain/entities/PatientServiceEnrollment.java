package org.lamisplus.modules.patient.domain.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "patient_service_enrollment")
@Data
@EqualsAndHashCode
public class PatientServiceEnrollment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "date_enrollment")
    private LocalDate dateEnrollment;

    @Column(name = "identifier_value")
    private String identifierValue;

    @Column(name = "date_exist")
    private LocalDate dateExist;

    @JoinColumn(name = "services_id")
    @ManyToOne
    private Service service;

    @JoinColumn(name = "identifier_type_id")
    @ManyToOne
    private IdentifierType identifierType;

    @JoinColumn(name = "patient_id")
    @ManyToOne
    private Patient patient;


}
