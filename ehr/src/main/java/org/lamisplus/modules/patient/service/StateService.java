package org.lamisplus.modules.patient.service;

import org.lamisplus.modules.patient.domain.dto.BadRequestAlertException;
import org.lamisplus.modules.patient.domain.entities.Country;
import org.lamisplus.modules.patient.domain.entities.State;
import org.lamisplus.modules.patient.repositories.CountriesRepository;
import org.lamisplus.modules.patient.repositories.StateRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
@Transactional
public class StateService {
    private final StateRepository stateRepository;
    private final CountriesRepository countriesRepository;

    public StateService(StateRepository stateRepository, CountriesRepository countriesRepository) {
        this.stateRepository = stateRepository;
        this.countriesRepository = countriesRepository;
    }

    public State save(@RequestBody State state) {
        Optional<Country> country = this.countriesRepository.findById(state.getCountry().getId());
        if (country.isPresent()){
            Country country1 = country.get();
            state.setCountry(country1);
            return this.stateRepository.save(state);
        } else throw new BadRequestAlertException("Country id did not exist ", "", "id is null");

    }


    public State update(@RequestBody State state) {
        ModelMapper modelMapper = new ModelMapper();
        State state1 = modelMapper.map(state, State.class);
        Country country = this.countriesRepository.getOne(state.getCountry().getId());
        state1.setCountry(country);
        return this.stateRepository.save(state1);
    }

}
