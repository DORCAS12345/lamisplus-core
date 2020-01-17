package org.lamisplus.modules.patient.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode
@Table(name = "person")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "other_names")
    private String otherNames;

    @JoinColumn(name = "marital_status_id")
    @ManyToOne
    private Codifier maritalStatus;

    @Basic
    @Column(name = "dob")
    private LocalDate dob;

    @Basic
    @Column(name = "dob_estimated")
    private Boolean dobEstimated;

    @JoinColumn(name = "gender_id")
    @ManyToOne
    private Codifier gender;

    @JoinColumn(name = "education_id")
    @ManyToOne
    private Codifier education;

    @JoinColumn(name = "occupation_id")
    @ManyToOne
    private Codifier occupation;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "person")
    @JsonIgnore
    private PersonContact personContact;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "person")
    @JsonIgnore
    private List<PersonRelative> personRelative;



}
