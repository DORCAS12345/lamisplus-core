package org.lamisplus.modules.patient.domain.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "service_form")
@Data
@EqualsAndHashCode
public class ServiceForm implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @JoinColumn(name = "service_id")
    @ManyToOne
    private Service services;

    @Column(name = "form_code")
    private String formCode;

    @Basic
    @Column(name = "form_url")
    @Lob
    private String formUrl;

    @Basic
    @Column(name = "json_form")
    @Lob
    private String jsonForm;

    @Basic
    @Column(name = "form_version")
    private String formVersion;
}
