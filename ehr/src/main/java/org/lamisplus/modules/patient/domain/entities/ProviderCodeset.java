package org.lamisplus.modules.patient.domain.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "provider_codeset")
@Data
@EqualsAndHashCode
public class ProviderCodeset implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic
    @Column(name = "long_name")
    private String longName;
    @Basic
    @Column(name = "short_name")
    private String shortName;
    @Basic
    @Column(name = "code")
    private String code;
    @Basic
    @Column(name = "description")
    private String description;

    @JoinColumn(name = "provider_id")
    @ManyToOne
    private Provider provider;


}
