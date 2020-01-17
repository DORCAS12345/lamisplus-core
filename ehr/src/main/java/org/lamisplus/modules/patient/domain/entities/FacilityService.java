package org.lamisplus.modules.patient.domain.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "facility_service")
@Data
@EqualsAndHashCode
public class FacilityService implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic
    @Column(name = "day_service")
    private String dayService;
    @Basic
    @Column(name = "time_service")
    private String timeService;
    @Basic
    @Column(name = "charge")
    private Boolean charge;

    @JoinColumn(name = "facility_id")
    @ManyToOne
    private Facility facility;


}
