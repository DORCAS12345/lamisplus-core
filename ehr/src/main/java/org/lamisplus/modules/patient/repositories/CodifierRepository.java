package org.lamisplus.modules.patient.repositories;


import org.lamisplus.modules.patient.domain.entities.Codifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CodifierRepository extends JpaRepository<Codifier, Long> {

    List<Codifier> findByCodifierGroup(String system);
}
