package org.lamisplus.modules.patient.domain.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "identifier_type")
@Data
@EqualsAndHashCode
public class IdentifierType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;

    @Basic
    @Column(name = "identifier_type_name")
    private String identifierTypeName;

    @Basic
    @Column(name = "validator")
    private String validator;
}
