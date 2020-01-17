package org.lamisplus.modules.patient.repositories;

import org.lamisplus.modules.patient.domain.entities.Patient;
import org.lamisplus.modules.patient.domain.entities.PatientServiceEnrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface PatientServicesEnrollmentRepository extends JpaRepository<PatientServiceEnrollment, Long> {

  PatientServiceEnrollment findByPatient(Patient patient);

  List<PatientServiceEnrollment> findByIdentifierValueContainingIgnoreCase(String identifyValue);
}
