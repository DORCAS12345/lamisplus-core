package org.lamisplus.modules.patient.res;

import org.lamisplus.modules.patient.domain.dto.BadRequestAlertException;
import org.lamisplus.modules.patient.domain.dto.HeaderUtil;
import org.lamisplus.modules.patient.domain.dto.PatientVisitRequest;
import org.lamisplus.modules.patient.domain.entities.*;
import org.lamisplus.modules.patient.repositories.CodifierRepository;
import org.lamisplus.modules.patient.repositories.PatientRepository;
import org.lamisplus.modules.patient.repositories.PatientVisitRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/patient-visit")
public class PatientVisitResource {
    private static final String ENTITY_NAME = "patient-visit";
    private final PatientVisitRepository patientVisitRepository;
    private final PatientRepository patientRepository;
    private final CodifierRepository codifierRepository;

    public PatientVisitResource(PatientVisitRepository patientVisitRepository, PatientRepository patientRepository, CodifierRepository codifierRepository) {
        this.patientVisitRepository = patientVisitRepository;
        this.patientRepository = patientRepository;
        this.codifierRepository = codifierRepository;
    }

    private static Object exist(PatientVisit o) {
        throw new BadRequestAlertException("Patient Visit Already Exist", ENTITY_NAME, "id Already Exist");
    }

    private static PatientVisit notExit() {
        throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "id is null");
    }


    @PostMapping
    public ResponseEntity save(@RequestBody PatientVisitRequest patientVisitRequest) throws URISyntaxException {
        patientVisitRepository.findById(patientVisitRequest.getId()).map(PatientVisitResource::exist);
        PatientVisit patientVisit = new PatientVisit();
        Patient patient = patientRepository.getOne(patientVisitRequest.getPatientId());
        patientVisit.setPatient(patient);
        patientVisit.setDateVisitStart(patientVisitRequest.getDateVisitStart());
        patientVisit.setTimeVisitStart(patientVisitRequest.getTimeVisitStart());
        Codifier codifier = codifierRepository.getOne(patientVisitRequest.getVisitTypeId());
        patientVisit.setVisitTypeId(codifier);
        PatientVisit result = patientVisitRepository.save(patientVisit);
        return ResponseEntity.created(new URI("/api/patient-visit/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, String.valueOf(result.getId()))).body(result);
    }

    @PutMapping
    public ResponseEntity update(@RequestBody PatientVisitRequest patientVisitRequest) throws URISyntaxException {
        Optional<PatientVisit> country1 = this.patientVisitRepository.findById(patientVisitRequest.getId());
        country1.orElseGet(PatientVisitResource::notExit);
        PatientVisit patientVisit = new PatientVisit();
        Patient patient = patientRepository.getOne(patientVisitRequest.getPatientId());
        patientVisit.setPatient(patient);
        patientVisit.setDateVisitStart(patientVisitRequest.getDateVisitStart());
        patientVisit.setTimeVisitStart(patientVisitRequest.getTimeVisitStart());
        Codifier codifier = codifierRepository.getOne(patientVisitRequest.getVisitTypeId());
        patientVisit.setVisitTypeId(codifier);
        PatientVisit result = patientVisitRepository.save(patientVisit);
        return ResponseEntity.created(new URI("/api/patient-visit/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, String.valueOf(result.getId())))
                .body(result);
    }

    @GetMapping
    public ResponseEntity<List<PatientVisitRequest>> getAllPatientVisit() {
        List<PatientVisitRequest> patientVisitRequestList = new ArrayList<>();
        List<PatientVisit> patientVisit = patientVisitRepository.findAll();
        patientVisit.forEach(patientVisit1 -> {
            PatientVisitRequest patientVisitRequest = new PatientVisitRequest();
            patientVisitRequest.setId(patientVisit1.getId());
            patientVisitRequest.setPatientId(patientVisit1.getPatient().getId());
            patientVisitRequest.setDateVisitStart(patientVisit1.getDateVisitStart());
            patientVisitRequest.setDateVisitStart(patientVisit1.getDateVisitStart());
            patientVisitRequest.setVisitTypeId(patientVisit1.getVisitTypeId().getId());
            patientVisitRequestList.add(patientVisitRequest);
        });
        return ResponseEntity.ok(patientVisitRequestList);

    }


    @GetMapping("/{id}")
    public ResponseEntity<List<PatientVisitRequest>> getSingle(@PathVariable Long id) {
        Optional<PatientVisit> patientVisit = patientVisitRepository.findById(id);
        patientVisit.map(patientVisit1 -> {
            PatientVisitRequest patientVisitRequest = new PatientVisitRequest();
            patientVisitRequest.setPatientId(patientVisit1.getPatient().getId());
            patientVisitRequest.setDateVisitStart(patientVisit1.getDateVisitStart());
            patientVisitRequest.setDateVisitStart(patientVisit1.getDateVisitStart());
            patientVisitRequest.setVisitTypeId(patientVisit1.getVisitTypeId().getId());
            return patientVisitRequest;
        });
        throw new BadRequestAlertException("No record found", ENTITY_NAME, "id is null");
    }
}
