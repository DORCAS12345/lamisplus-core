package org.lamisplus.modules.patient.repositories;

import org.lamisplus.modules.patient.domain.entities.Person;
import org.lamisplus.modules.patient.domain.entities.PersonContact;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonContactsRepository extends JpaRepository<PersonContact,Long> {
    PersonContact findByPerson(Person person);
}
