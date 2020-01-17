package org.lamisplus.modules.patient.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "codifier")
@Data
@EqualsAndHashCode
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Codifier implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
  //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "codifier_group")
    private String codifierGroup;

    @Column(name = "version")
    private String version;

    @Column(name = "code")
    private String code;

    @Column(name = "display")
    private String display;

    @Column(name = "language")
    private String language;

    @Column(name = "active")
    private Boolean active = Boolean.TRUE;
}
