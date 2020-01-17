package org.lamisplus.modules.patient.repositories;

import org.lamisplus.modules.patient.domain.entities.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModuleRepository extends JpaRepository<Module, Long> {
    Module findByName(String name);
}
