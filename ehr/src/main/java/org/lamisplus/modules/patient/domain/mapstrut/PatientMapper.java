package org.lamisplus.modules.patient.domain.mapstrut;
import org.lamisplus.modules.patient.domain.dto.PatientRequest;
import org.lamisplus.modules.patient.domain.entities.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PatientMapper {
    PatientMapper INSTANCE = Mappers.getMapper(PatientMapper.class);
    @Mappings({
            @Mapping(source = "person.selectOption.id", target = "genderId"),
            @Mapping(source = "person.selectOption.id", target = "educationId"),
            @Mapping(source = "person.selectOption.id", target = "occupationId"),
            @Mapping(source = "person.personContact", target = "personContact"),
    })
    Patient PatientDTOToPatient(PatientRequest dto);

    PatientRequest PatientToPatientDTO(Patient patient);
}
