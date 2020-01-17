package org.lamisplus.modules.patient.res;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.BucketOrder;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.lamisplus.modules.patient.domain.dto.*;
import org.lamisplus.modules.patient.domain.entities.*;
import org.lamisplus.modules.patient.repositories.*;
import org.lamisplus.modules.patient.service.PatientServices;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/patient")
@Slf4j
public class PatientResources {

    private static final String ENTITY_NAME = "patient";
    private final PatientRepository patientRepository;
    private final PatientServices patientService;
    private final PersonRepository personRepository;
    private final PatientServicesEnrollmentRepository patientServiceEnrollmentRepository;
    private final CodifierRepository codifierRepository;

    public PatientResources(PatientRepository patientRepository, PatientServices patientService, PersonRepository personRepository, PatientServicesEnrollmentRepository patientServiceEnrollmentRepository, CodifierRepository codifierRepository) {
        this.patientRepository = patientRepository;
        this.patientService = patientService;
        this.personRepository = personRepository;
        this.patientServiceEnrollmentRepository = patientServiceEnrollmentRepository;
        this.codifierRepository = codifierRepository;
    }


    @PostMapping
    public ResponseEntity<Person> save(@RequestBody PatientRequest patientRequest) throws URISyntaxException {
        Person person = this.patientService.save(patientRequest);
        return ResponseEntity.created(new URI("/api/patient/" + person.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, String.valueOf(person.getId()))).body(person);
    }

    @PutMapping
    public ResponseEntity<Person> update(@RequestBody PatientRequest personDTO) throws URISyntaxException {
        Person result = this.patientService.update(personDTO);
        return ResponseEntity.created(new URI("/api/patient/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, String.valueOf(result.getId())))
                .body(result);
    }

    @GetMapping
    public ResponseEntity getAllPatients() {
        return ResponseEntity.ok(patientService.getAllPatient());
    }


    @GetMapping("/{id}")
    public ResponseEntity getSingle(@PathVariable Long id) {
        PatientResponseDTO patientResponseDTO = patientService.getSingle(id);
        return ResponseEntity.ok(patientResponseDTO);
    }

    @GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity searchPatient(@RequestParam(name = "search") String search) {
        List<PatientServiceEnrollment> patientServiceEnrollment1 = patientServiceEnrollmentRepository.findByIdentifierValueContainingIgnoreCase(search);
      if(!patientServiceEnrollment1.isEmpty()){
          List<PatientDemography> patientDemography2 = new ArrayList<>();
          patientServiceEnrollment1.forEach(patientServiceEnrollment -> {
              PatientDemography patientDemography = new PatientDemography();
              Patient patient = patientRepository.getOne(patientServiceEnrollment.getPatient().getId());
              patientDemography.setPatientId(patient.getId());
              patientDemography.setDateRegistration(patient.getDateRegistration());
              patientDemography.setHospitalNumber(patientServiceEnrollment.getIdentifierValue());
              patientDemography.setPersonId(patient.getPerson().getId());
              patientDemography.setFirstName(patient.getPerson().getFirstName());
              patientDemography.setLastName(patient.getPerson().getLastName());
              patientDemography.setOtherNames(patient.getPerson().getOtherNames());
              Codifier maritalStatus = codifierRepository.getOne(patient.getPerson().getMaritalStatus().getId());
              patientDemography.setTitle(maritalStatus.getDisplay());
              patientDemography.setDob(patient.getPerson().getDob());
              patientDemography.setDobEstimated(patient.getPerson().getDobEstimated());
              Codifier gender = codifierRepository.getOne(patient.getPerson().getGender().getId());
              patientDemography.setGender(gender.getDisplay());
              Codifier education = codifierRepository.getOne(patient.getPerson().getEducation().getId());
              patientDemography.setEducation(education.getDisplay());
              Codifier occupation = codifierRepository.getOne(patient.getPerson().getOccupation().getId());
              patientDemography.setOccupation(occupation.getDisplay());
              patientDemography2.add(patientDemography);
          });
          return ResponseEntity.ok(patientDemography2);
      }
        List<PatientDemography> patientDemography1 = new ArrayList<>();
        List<Person> personList = this.personRepository.findByFirstNameLastName(search);
        personList.forEach(person -> {
            PatientDemography patientDemography = new PatientDemography();
            Patient patient = patientRepository.findByPerson(person);
            patientDemography.setPatientId(patient.getId());
            patientDemography.setDateRegistration(patient.getDateRegistration());
            PatientServiceEnrollment patientServiceEnrollment = patientServiceEnrollmentRepository.findByPatient(patient);
            patientDemography.setHospitalNumber(patientServiceEnrollment.getIdentifierValue());
            patientDemography.setPersonId(person.getId());
            patientDemography.setFirstName(person.getFirstName());
            patientDemography.setLastName(person.getLastName());
            patientDemography.setOtherNames(person.getOtherNames());
            Codifier maritalStatus = codifierRepository.getOne(patient.getPerson().getMaritalStatus().getId());
            patientDemography.setTitle(maritalStatus.getDisplay());
            patientDemography.setDob(person.getDob());
            patientDemography.setDobEstimated(person.getDobEstimated());
            Codifier gender = codifierRepository.getOne(patient.getPerson().getGender().getId());
            patientDemography.setGender(gender.getDisplay());
            Codifier education = codifierRepository.getOne(patient.getPerson().getEducation().getId());
            patientDemography.setEducation(education.getDisplay());
            Codifier occupation = codifierRepository.getOne(patient.getPerson().getOccupation().getId());
            patientDemography.setOccupation(occupation.getDisplay());
            patientDemography1.add(patientDemography);
        });
        return ResponseEntity.ok(patientDemography1);


//
//        @PostMapping("/_search/patients")
//        public ResponseEntity<List<Patient>> getAllPatients1(@RequestParam String query, @RequestBody List<AggregateVM> aggregates,
//                Pageable pageable) {
//            LOG.debug("REST request to search Patients: {}; page: {}", query, pageable);
//            TermsAggregationBuilder genderAggregation = AggregationBuilders.terms("gender_tags")
//                    .field("gender")
//                    .order(BucketOrder.count(true));
//            TermsAggregationBuilder statusAggregation = AggregationBuilders.terms("status_tags")
//                    .field("currentStatus")
//                    .order(BucketOrder.count(true));
//            TermsAggregationBuilder entryAggregation = AggregationBuilders.terms("entry_tags")
//                    .field("entryPoint")
//                    .order(BucketOrder.count(true));
//            TermsAggregationBuilder biometricAggregation = AggregationBuilders.terms("biometric_tags")
//                    .field("biometric")
//                    .order(BucketOrder.count(true));
//            NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder()
//                    .withIndices("patient")
//                    .addAggregation(genderAggregation)
//                    .addAggregation(statusAggregation)
//                    .addAggregation(biometricAggregation)
//                    .addAggregation(entryAggregation)
//                    .withPageable(pageable);
//
//            BoolQueryBuilder filter = null;// = FilterUtil.getFacilityFilterForCurrentUser();
//            for (AggregateVM aggregate : aggregates) {
//                if (StringUtils.isNotBlank(aggregate.getField())) {
//                    filter = filter
//                            .filter(QueryBuilders.termQuery(aggregate.getField(), aggregate.getKey()));
//                }
//            }
//
//            if (StringUtils.isBlank(query)) {
//                queryBuilder = queryBuilder.withQuery(
//                        QueryBuilders.boolQuery()
//                                .filter(filter)
//                                .must(QueryBuilders.matchAllQuery())
//                );
//            } else {
//                QueryBuilder booleanQuery = QueryBuilders.boolQuery();
//                BoolQueryBuilder builder = QueryBuilders.boolQuery()
//                        .should(
//                                QueryBuilders.matchQuery("surname", query)
//                                        .fuzziness(Fuzziness.ONE)
//                                        .prefixLength(1)
//                        )
//                        .should(
//                                QueryBuilders.matchQuery("otherNames", query)
//                                        .fuzziness(Fuzziness.ONE)
//                                        .prefixLength(1)
//                        )
//                        .should(
//                                QueryBuilders.termQuery("hospitalNum", query)
//                                        .boost(50)
//                        )
//                        .should(
//                                QueryBuilders.termQuery("uniqueId", query)
//                                        .boost(50)
//                        )
//                        .should(
//                                QueryBuilders.termQuery("phone", query)
//                                        .boost(50)
//                        );
//
//                if (aggregates.size() > 0) {
//                    builder = builder.filter(filter);
//                }
//                queryBuilder = queryBuilder.withQuery(builder);
//            }
//            SearchQuery searchQuery = queryBuilder.build()
//                    .setPageable(pageable)
//                    .addSort(pageable.getSort());
//
//            AggregatedPage<Patient> page = searchTemplate.queryForPage(searchQuery, Patient.class);
//            List<AggregateVM> responseAggregates = new ArrayList<>();
//
//            if (page.hasAggregations()) {
//                TermsAggregation aggregation = page.getAggregation("status_tags", TermsAggregation.class);
//                responseAggregates.addAll(
//                        AggregateUtil.getAggregates(aggregation, "currentStatus", "Current Status"));
//                aggregation = page.getAggregation("entry_tags", TermsAggregation.class);
//                responseAggregates.addAll(
//                        AggregateUtil.getAggregates(aggregation, "entryPoint", "Entry Point"));
//                aggregation = page.getAggregation("gender_tags", TermsAggregation.class);
//                responseAggregates.addAll(
//                        AggregateUtil.getAggregates(aggregation, "gender", "Gender"));
//                aggregation = page.getAggregation("biometric_tags", TermsAggregation.class);
//                responseAggregates.addAll(
//                        AggregateUtil.getAggregates(aggregation, "biometric", "Has Biometrics"));
//            }
//
//            Map<String, List<AggregateVM>> map = AggregateUtil.aggregateMap(responseAggregates);
//
//            ObjectMapper objectMapper = new ObjectMapper();
//            HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/patients");
//            try {
//                String aggs = objectMapper.writeValueAsString(map);
//                headers.add("Aggregates", aggs);
//            } catch (JsonProcessingException e) {
//                e.printStackTrace();
//            }
//            return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
//        }
//
//    }

    }
}
