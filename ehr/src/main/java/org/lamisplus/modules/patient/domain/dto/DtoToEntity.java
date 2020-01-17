package org.lamisplus.modules.patient.domain.dto;

import lombok.extern.slf4j.Slf4j;
import org.lamisplus.modules.patient.domain.entities.*;
import org.modelmapper.ModelMapper;

@Slf4j
public class DtoToEntity {


    public static Person convertPersonDTOToPerson(PersonRequest personRequest) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(personRequest, Person.class);
    }


    public static PersonResponse convertPersonToPersonDTO1(Person person) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(person, PersonResponse.class);
    }


    public static PersonContact convertPersonContactDTOToPersonContact(PersonContactRequest personContactRequest) {
        ModelMapper modelMapper = new ModelMapper();

        return modelMapper.map(personContactRequest, PersonContact.class);
    }


    public static PersonRelative convertPersonRelativeDTORelative(PersonRelatives personRelatives) {
        return new ModelMapper().map(personRelatives, PersonRelative.class);
    }


}
