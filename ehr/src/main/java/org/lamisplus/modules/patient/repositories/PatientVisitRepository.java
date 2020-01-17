package org.lamisplus.modules.patient.repositories;

import org.lamisplus.modules.patient.domain.entities.PatientVisit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientVisitRepository extends JpaRepository<PatientVisit , Long> {
}
