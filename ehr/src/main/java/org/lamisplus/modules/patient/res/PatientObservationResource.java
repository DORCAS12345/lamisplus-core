package org.lamisplus.modules.patient.res;

import lombok.extern.slf4j.Slf4j;
import org.lamisplus.modules.patient.domain.dto.HeaderUtil;
import org.lamisplus.modules.patient.domain.dto.ObservationRequest;
import org.lamisplus.modules.patient.domain.entities.PatientObservation;

import org.lamisplus.modules.patient.repositories.PatientObservationRepository;
import org.lamisplus.modules.patient.service.PatientObservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/observation")
@Slf4j
public class PatientObservationResource {
    private static final String ENTITY_NAME = "observation";
    private final PatientObservationService patientObservationService;
    private final PatientObservationRepository patientObservationRepository;

    public PatientObservationResource(PatientObservationService patientObservationService, PatientObservationRepository patientObservationRepository) {
        this.patientObservationService = patientObservationService;
        this.patientObservationRepository = patientObservationRepository;
    }


    @PostMapping
    public ResponseEntity<PatientObservation> save(@RequestBody ObservationRequest observation) throws URISyntaxException {
        PatientObservation result = this.patientObservationService.save(observation);
        return ResponseEntity.created(new URI("/api/patient-observations/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
                .body(result);
    }


    @PutMapping
    public ResponseEntity<PatientObservation> update(@RequestBody ObservationRequest observation) {
        PatientObservation result = this.patientObservationService.save(observation);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, observation.getId().toString()))
                .body(result);
    }


    @GetMapping("/{id}")
    public ResponseEntity getObservation(@PathVariable Long id) {
        PatientObservation patientObservation = this.patientObservationRepository.getOne(id);
        return ResponseEntity.ok(patientObservation);

    }

    @GetMapping("/patient/{id}/{form-code}/{value}")
    public ResponseEntity<List<PatientObservation>> getPatientObservations(@PathVariable("id") Long id, @PathVariable("form-code") Long formCode,@PathVariable String value) {
        List<PatientObservation> patientObservationList = this.patientObservationService.getAllObservationByPatientIdAndFormCode(id,formCode,value);
        return ResponseEntity.ok(patientObservationList);
    }



    @GetMapping("/patient/{id}/encounter/{encounterId}")
    public ResponseEntity<List<PatientObservation>> getPatientObservationsForEncounter(@PathVariable Long id, @PathVariable Long encounterId) {
        List<PatientObservation> patientObservationList = this.patientObservationService.getPatientObservationsForEncounter(id, encounterId);
        return ResponseEntity.ok(patientObservationList);
    }



}
