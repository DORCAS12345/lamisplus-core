package org.lamisplus.modules.patient.repositories;

import org.lamisplus.modules.patient.domain.entities.Person;
import org.lamisplus.modules.patient.domain.entities.PersonRelative;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PersonRelativeRepository extends JpaRepository<PersonRelative, Long> {
    List<PersonRelative> findByPerson(Person person);


}
