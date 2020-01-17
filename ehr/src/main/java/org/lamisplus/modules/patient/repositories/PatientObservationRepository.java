package org.lamisplus.modules.patient.repositories;
import org.lamisplus.modules.patient.domain.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PatientObservationRepository extends JpaRepository<PatientObservation, Long> {
    //like lower(concat('%', :search, '%'))data->>'name' = :name %?1%
    //select * from module_data where data->>'title' like '%Board%'
    @Query(value = "select * from patient_observation where patient_observation.patient_id = ?1  and form_code = ?2 and form_data->>'titile' =?3", nativeQuery = true)
    List<PatientObservation> findByPatientAndFormCodeTitle(Patient patient, Long formCode, String title);
/*
this query select jsonB from patient_observation by json name
    @Query(value = "SELECT form_data->>'title' FROM patient_observation",nativeQuery = true)
    List<Object> findByJsonBColumnName();
*/
/*
JSONParser parser = new JSONParser();
JSONObject json = (JSONObject) parser.parse(stringToParse);
 */

    /*
    this query is filter for who's published status is false
        @Query(value = "SELECT * FROM patient_observation WHERE form_data->>'published' = 'false'",nativeQuery = true)
        List<PatientObservation> findBySpecificValue();
    */




//        @Query(value = "SELECT form_data->>'genres'" +
//                "    FROM patient_observation" +
//                "    WHERE patient_observation.patient_id = 1  and form_code =1 ;",nativeQuery = true)
//        List<Object> findByObjectFromJsonB();


    List<PatientObservation> findByPatientAndEncounter(Patient patient, PatientEncounter encounter);

}
