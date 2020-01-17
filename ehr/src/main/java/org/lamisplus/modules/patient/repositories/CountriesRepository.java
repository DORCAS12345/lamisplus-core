package org.lamisplus.modules.patient.repositories;

import org.lamisplus.modules.patient.domain.entities.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountriesRepository extends JpaRepository<Country, Long> {

}
