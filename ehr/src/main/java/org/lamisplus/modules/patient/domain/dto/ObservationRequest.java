package org.lamisplus.modules.patient.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ObservationRequest implements Serializable {
    private Long id;
    @NotNull
    private Object formData;
    @NotNull
    private String formCode;
    @NotNull
    private Long patientId;
    @NotNull
    private Long serviceId;
    @NotNull
    private LocalDate dateEncounter;


}
