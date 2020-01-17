package org.lamisplus.modules.patient.repositories;

import org.lamisplus.modules.patient.domain.entities.IdentifierType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IdentifierTypesRepository extends JpaRepository<IdentifierType, Long> {
    IdentifierType findByIdentifierTypeName(String name);
}
