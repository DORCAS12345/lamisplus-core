package org.lamisplus.modules.patient.repositories;

import org.lamisplus.modules.patient.domain.entities.Patient;
import org.lamisplus.modules.patient.domain.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    Patient findByPerson(Person person);

}