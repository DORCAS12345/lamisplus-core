package org.lamisplus.modules.patient.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "address")
@Data

public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @Column(name = "zip_code")
    private String zipCode;
    @Column(name = "landmark")
    private String landmark;
    @Column(name = "street_address")
    private String streetAddress;
    @Column(name = "city")
    private String city;

    @JoinColumn(name = "province_id")
    @ManyToOne(cascade=CascadeType.ALL)
    private Province province;

    @JoinColumn(name = "state_id")
    @ManyToOne(cascade=CascadeType.ALL)
    private State state;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "country_id")
    private Country country;

    @JoinColumn(name = "person_contact_id")
    @ManyToOne(fetch=FetchType.LAZY)
    @JsonIgnore
    private PersonContact personContact;
}