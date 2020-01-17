package org.lamisplus.modules.patient.domain.entities;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "person_relative")
public class PersonRelative implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id")
    private Long id;

    @JoinColumn(name = "person_id")
    @ManyToOne
    private Person person;

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "other_names")
    private String otherNames;
    @Column(name = "email")
    private String email;
    @Column(name = "mobile_phone_number")
    private String mobilePhoneNumber;
    @Column(name = "alternate_phone_number")
    private String alternatePhoneNumber;
    @Column(name = "address")
    private String address;
    @JoinColumn(name = "relationship_type_id")
    @ManyToOne
    private Codifier relationshipType;
}
