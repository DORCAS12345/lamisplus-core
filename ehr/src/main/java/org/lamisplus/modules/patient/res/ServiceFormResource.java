package org.lamisplus.modules.patient.res;

import org.lamisplus.modules.patient.domain.dto.BadRequestAlertException;
import org.lamisplus.modules.patient.domain.dto.HeaderUtil;
import org.lamisplus.modules.patient.domain.entities.ServiceForm;
import org.lamisplus.modules.patient.repositories.*;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/service-form")
public class ServiceFormResource {
    private final ServiceFormRepository serviceFormRepository;
    private static final String ENTITY_NAME = "serviceForm";

    public ServiceFormResource(ServiceFormRepository serviceFormRepository) {
        this.serviceFormRepository = serviceFormRepository;
    }

    private static Object exist(ServiceForm o) {
        throw new BadRequestAlertException("Service Form Already Exist", ENTITY_NAME, "id Already Exist");
    }

    private static ServiceForm notExit() {
        throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "id is null");
    }

    @PostMapping
    public ResponseEntity<ServiceForm> save(@RequestBody ServiceForm serviceForm1) throws URISyntaxException {
        Optional<ServiceForm> serviceForm = this.serviceFormRepository.findById(serviceForm1.getId());
        serviceForm.map(ServiceFormResource::exist);
        ServiceForm result = this.serviceFormRepository.save(serviceForm1);
        return ResponseEntity.created(new URI("/api/service-form/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, String.valueOf(result.getId()))).body(result);
    }


    @PutMapping
    public ResponseEntity<ServiceForm> update(@RequestBody ServiceForm serviceForm1) throws URISyntaxException {
        Optional<ServiceForm> serviceForm = this.serviceFormRepository.findById(serviceForm1.getId());
        serviceForm.orElseGet(ServiceFormResource::notExit);
        ServiceForm result = this.serviceFormRepository.save(serviceForm1);
        return ResponseEntity.created(new URI("/api/service-form/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, String.valueOf(result.getId())))
                .body(result);
    }

    @GetMapping
    public ResponseEntity<List<ServiceForm>> getAllServiceForm() {
        return ResponseEntity.ok(this.serviceFormRepository.findAll());
    }


    @GetMapping("/{form-code}")
    public ResponseEntity<ServiceForm> getServiceByFormCode(@PathVariable("form-code") String formCode) {
        return ResponseEntity.ok(this.serviceFormRepository.findByFormCode(formCode));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceForm> getServiceById(@PathVariable Long id) {
        Optional<ServiceForm> serviceForm = this.serviceFormRepository.findById(id);
        if (serviceForm.isPresent()){
            ServiceForm serviceForm2 = serviceForm.get();
            return ResponseEntity.ok(serviceForm2);
        } else throw new BadRequestAlertException("Record not found with id ", ENTITY_NAME, "id is  Null");
    }


}
