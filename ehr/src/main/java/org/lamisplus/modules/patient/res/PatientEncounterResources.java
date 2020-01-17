package org.lamisplus.modules.patient.res;

import org.lamisplus.modules.patient.domain.dto.BadRequestAlertException;
import org.lamisplus.modules.patient.domain.dto.HeaderUtil;
import org.lamisplus.modules.patient.domain.entities.PatientEncounter;
import org.lamisplus.modules.patient.repositories.PatientEncounterRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

@RestController
@RequestMapping("/api/patient-encounter")
public class PatientEncounterResources {
    private static String ENTITY_NAME = "patient-encounter";
    private final PatientEncounterRepository patientEncounterRepository;

    public PatientEncounterResources(PatientEncounterRepository patientEncounterRepository) {
        this.patientEncounterRepository = patientEncounterRepository;
    }

    private static Object exist(PatientEncounter patientEncounter) {
        throw new BadRequestAlertException("Patient Encounter Already Exist", ENTITY_NAME, "id Already Exist");
    }

    private static PatientEncounter notExit() {
        throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "id is null");
    }

    @PostMapping
    public ResponseEntity<PatientEncounter> save(@RequestBody PatientEncounter patientEncounter) throws URISyntaxException {
        Optional<PatientEncounter> patient1 = this.patientEncounterRepository.findById(patientEncounter.getId());
        patient1.map(PatientEncounterResources::exist);
        PatientEncounter result = patientEncounterRepository.save(patientEncounter);
        return ResponseEntity.created(new URI("/api/patient-encounter/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, String.valueOf(result.getId()))).body(result);
    }

    @PutMapping
    public ResponseEntity<PatientEncounter> update(@RequestBody PatientEncounter patientEncounter) throws URISyntaxException {
        Optional<PatientEncounter> patient1 = this.patientEncounterRepository.findById(patientEncounter.getId());
        patient1.orElseGet(PatientEncounterResources::notExit);
        PatientEncounter result = patientEncounterRepository.save(patientEncounter);
        return ResponseEntity.created(new URI("/api/patient-encounter/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, String.valueOf(result.getId()))).body(result);
    }

    @GetMapping
    public ResponseEntity getAllEncounter() {
        return ResponseEntity.ok(patientEncounterRepository.findAll());
    }


    @GetMapping("/{id}")
    public ResponseEntity getSingle(@PathVariable Long id) {
        Optional<PatientEncounter> patientEncounter = this.patientEncounterRepository.findById(id);
        patientEncounter.orElseGet(PatientEncounterResources::notExit);
        PatientEncounter patientEncounter1 = patientEncounter.get();
        return ResponseEntity.ok(patientEncounter1);
    }

}
