package org.lamisplus.modules.patient.domain.dto;

import lombok.Data;

import java.time.LocalDate;
@Data
public class PatientResponseDTO {
    private Long id;
    private LocalDate dateRegistration;
    private String hospitalNumber;
    private PersonResponse person;
}
