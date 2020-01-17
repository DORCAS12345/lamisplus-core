package org.lamisplus.modules.patient.repositories;

import org.lamisplus.modules.patient.domain.entities.Country;
import org.lamisplus.modules.patient.domain.entities.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
@Repository
public interface StateRepository extends JpaRepository<State, Long> {
    List<State> findByCountry(Country country);
}
