package com.ayunyamiento.papantla.papantla_informa.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalTime;
import java.util.List;

@Entity(name = "service")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentServiceModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long serviceId;

    @Column(nullable = false)
    private String identifyWord;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", nullable = false)
    private DepartmentModel departmentModel;

    @Column(nullable = false)
    private String serviceName;
    @Column(nullable = false)
    private String serviceDescription;

    // 🔹 Relación con los requisitos del servicio
    @OneToMany(mappedBy = "service", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<RequirementsModel> requirementsModels;

    // 🔹 Relación con los precios del servicio
    @OneToMany(mappedBy = "service", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<ServicePricesModel> servicePricesModels;

    private Boolean status = false;
    @CreationTimestamp
    private LocalTime createdAt;
    @UpdateTimestamp
    private LocalTime updatedAt;

}
