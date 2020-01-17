package org.lamisplus.modules.patient.repositories;

import org.lamisplus.modules.patient.domain.entities.Province;
import org.lamisplus.modules.patient.domain.entities.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProvinceRepository extends JpaRepository<Province, Long> {
    List<Province> findByStateOrderByName(State state);
}
