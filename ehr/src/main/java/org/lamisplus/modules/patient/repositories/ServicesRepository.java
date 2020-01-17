package org.lamisplus.modules.patient.repositories;


import org.lamisplus.modules.patient.domain.entities.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicesRepository extends JpaRepository<Service, Long> {
    Service findByServiceName(String name);
}
