package org.lamisplus.modules.patient.domain.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode
@Table(name = "facility")
public class Facility implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;

    @Basic
    @Column(name = "physical_location")
    private String physicalLocation;

    @Basic
    @Column(name = "postal_address")
    private String postalAddress;

    @Basic
    @Column(name = "start_date")
    private LocalDate startDate;

    @Basic
    @Column(name = "ownership")
    private String ownership;

    @Basic
    @Column(name = "ownership_type")
    private String ownershipType;

    @Basic
    @Column(name = "facility_level")
    private String facilityLevel;

    @Basic
    @Column(name = "facility_level_option")
    private String facilityLevelOption;

    @Basic
    @Column(name = "longitude")
    private String longitude;

    @Basic
    @Column(name = "latitude")
    private String latitude;

    @Basic
    @Column(name = "facility_name")
    private String facilityName;

    @Basic
    @Column(name = "facility_code")
    private String facilityCode;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "facility")
    private List<FacilityService> facilityService;


}
