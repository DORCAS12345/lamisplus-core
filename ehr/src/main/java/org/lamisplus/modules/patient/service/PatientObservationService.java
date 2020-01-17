package org.lamisplus.modules.patient.service;

import lombok.extern.slf4j.Slf4j;;
import org.lamisplus.modules.patient.domain.dto.BadRequestAlertException;
import org.lamisplus.modules.patient.domain.dto.ObservationRequest;
import org.lamisplus.modules.patient.domain.entities.*;
import org.lamisplus.modules.patient.repositories.*;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;


@Transactional
@org.springframework.stereotype.Service
@Slf4j
public class PatientObservationService {
    private static final String ENTITY_NAME = "observation";
    @PersistenceContext
    private EntityManager em;
    private final PatientRepository patientRepository;
    private final PatientObservationRepository patientObservationRepository;
    private final ServicesRepository serviceRepository;
    private final PatientEncounterRepository encounterRepository;

    public PatientObservationService(PatientRepository patientRepository, PatientObservationRepository patientObservationRepository, ServicesRepository serviceRepository, PatientEncounterRepository encounterRepository) {
        this.patientRepository = patientRepository;
        this.patientObservationRepository = patientObservationRepository;
        this.serviceRepository = serviceRepository;
        this.encounterRepository = encounterRepository;
    }

    private static Object exist(PatientObservation o) {
        throw new BadRequestAlertException("Observation  Already Exist", ENTITY_NAME, "id Already Exist");
    }

    private static PatientObservation notExit() {
        throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "id is null");
    }
//check the enounter table if patientid, encounterdate, serviceid exist the return the encounterId
    //set to observation  but if it does not exist save the encounter and return the id.

    public PatientObservation save(ObservationRequest observation) {
        LOG.debug("REST: {}", observation);
        Patient patient1 = this.patientRepository.getOne(observation.getPatientId());
        Service service = this.serviceRepository.getOne(observation.getServiceId());
        Optional<PatientEncounter> patientEncounter = this.encounterRepository.findByPatientAndDateEncounterAndService(patient1, observation.getDateEncounter(), service);
        if (patientEncounter.isPresent()){
            PatientEncounter patientEncounter1 = patientEncounter.get();
            PatientObservation patientObservation = new PatientObservation();
            patientObservation.setFormCode(observation.getFormCode());
            patientObservation.setFormData(observation.getFormData());
            patientObservation.setPatient(patient1);
            patientObservation.setEncounter(patientEncounter1);
            return patientObservationRepository.save(patientObservation);
        } else {
            PatientEncounter patientEncounter1 = new PatientEncounter();
            patientEncounter1.setDateEncounter(observation.getDateEncounter());
            patientEncounter1.setPatient(patient1);
            patientEncounter1.setService(service);
            PatientEncounter patientEncounter2 = this.encounterRepository.save(patientEncounter1);
            PatientObservation patientObservation = new PatientObservation();
            patientObservation.setFormCode(observation.getFormCode());
            patientObservation.setFormData(observation.getFormData());
            patientObservation.setPatient(patient1);
            patientObservation.setEncounter(patientEncounter2);
            return patientObservationRepository.save(patientObservation);
        }
        //  return null;
    }
//
//    @Transactional(isolation = Isolation.SERIALIZABLE)
//    public void updateObservation(@NotNull Long patientId, @NotNull Object request) {
//        Objects.requireNonNull(patientId);
//        Objects.requireNonNull(request);
//        PatientObservation patientObservation = this.patientObservationRepository
//                .findById(patientId)
//                .orElseThrow(() -> new ResourceNotFoundException("Patient vital not found with id: " + patientId));
//        if (patientObservation.getFormData() == null){
//            patientObservation.setFormData(new ArrayList<>());
//        }
//        patientObservation.getFormData().add(request);
//
//    }


    public PatientObservation update(ObservationRequest observation) {
        Optional<PatientObservation> patientVisit = patientObservationRepository.findById(observation.getId());
        patientVisit.orElseGet(PatientObservationService::notExit);
        PatientObservation patientObservation = new PatientObservation();
        patientObservation.setFormCode(observation.getFormCode());
        patientObservation.setFormData(observation.getFormData());
        Patient patient = patientRepository.getOne(observation.getPatientId());
        patientObservation.setPatient(patient);
        return patientObservationRepository.save(patientObservation);

    }


    public List<PatientObservation> getAllObservationByPatientIdAndFormCode(Long id, Long formCode, String value) {
        Patient patient = patientRepository.getOne(id);
        return patientObservationRepository.findByPatientAndFormCodeTitle(patient,formCode,value);
    }


    public List<PatientObservation> getPatientObservationsForEncounter(Long id, Long encounterId) {
        Patient patient = patientRepository.getOne(id);
        PatientEncounter encounter = encounterRepository.getOne(encounterId);
        return patientObservationRepository.findByPatientAndEncounter(patient, encounter);
    }


}
