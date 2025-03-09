package com.ayunyamiento.papantla.papantla_informa.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalTime;

@Entity(name = "requirements")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequirementsModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long requirementId;

    @Column(nullable = false)
    private String identifyWord;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_id", nullable = false)
    private DepartmentServiceModel service;

    @Column(nullable = false)
    private String requirementName;
    @Column(nullable = true)
    private String requirementDescription;
    @Column(nullable = false)
    private Integer copies = 1;

    private Boolean isMandatory = true;

    private String notes;

    @CreationTimestamp
    private LocalTime createdAt;
    @UpdateTimestamp
    private LocalTime updatedAt;

}
