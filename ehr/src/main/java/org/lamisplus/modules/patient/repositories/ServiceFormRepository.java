package org.lamisplus.modules.patient.repositories;

import org.lamisplus.modules.patient.domain.entities.ServiceForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ServiceFormRepository extends JpaRepository<ServiceForm, Long> {
    ServiceForm findByFormCode(String formCode);
}
