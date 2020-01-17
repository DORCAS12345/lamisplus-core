package org.lamisplus.modules.patient.repositories;

import org.lamisplus.modules.patient.domain.entities.Patient;
import org.lamisplus.modules.patient.domain.entities.PatientEncounter;
import org.lamisplus.modules.patient.domain.entities.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface PatientEncounterRepository extends JpaRepository<PatientEncounter, Long> {
    Optional<PatientEncounter> findByPatientAndDateEncounterAndService(Patient patient, LocalDate encounterDate, Service service);
}
