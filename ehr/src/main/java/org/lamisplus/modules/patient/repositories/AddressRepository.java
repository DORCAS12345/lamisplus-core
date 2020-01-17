package org.lamisplus.modules.patient.repositories;

import org.lamisplus.modules.patient.domain.entities.Address;
import org.lamisplus.modules.patient.domain.entities.PersonContact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address , Long> {
    List<Address> findByPersonContact(PersonContact personContact);
}
