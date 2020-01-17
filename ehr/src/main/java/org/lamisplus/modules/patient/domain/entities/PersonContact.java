package org.lamisplus.modules.patient.domain.entities;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "person_contact")
@Data
@EqualsAndHashCode
public class PersonContact implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "mobile_phone_number")
    private String mobilePhoneNumber;

    @Column(name = "alternate__phone_number")
    private String alternatePhoneNumber;

    @Column(name = "email")
    private String email;

    @JoinColumn(name = "person_id")
    @OneToOne
    private Person person;

}
