package org.lamisplus.modules.patient.domain.dto;

import lombok.Data;

import java.util.List;

@Data
public class PersonContactRequest {
    private Long id;
    private String mobilePhoneNumber;
    private String alternatePhoneNumber;
    private String email;
    private Long personId;
    private List<AddressDTO> address;
}
