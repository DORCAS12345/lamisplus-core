package org.lamisplus.modules.patient.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@EqualsAndHashCode
@Data
@Table(name = "service")
public class Service implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "service_name")
    private String serviceName;

    @JoinColumn(name = "module_id")
    @ManyToOne
    @JsonIgnore
    private Module module;
}
