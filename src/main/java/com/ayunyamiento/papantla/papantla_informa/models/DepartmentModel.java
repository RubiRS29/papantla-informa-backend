package com.ayunyamiento.papantla.papantla_informa.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalTime;
import java.util.List;

@Entity(name = "department")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long departmentId;

    @Column(nullable = false, unique = true, length = 10)
    private String departmentIdentify;

    @Column(nullable = false)
    private String departmentName;
    @Column(nullable = false)
    private String departmentDescription;
    @Column(nullable = false)
    private String headOfDepartment;
    @Column(nullable = false)
    private String contactEmail;
    @Column(nullable = false, length = 10)
    private String contactPhone;
    @Column(nullable = true)
    private String address;

    // 🔹 Relación con los servicios del departamento
    @OneToMany(mappedBy = "departmentModel", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<DepartmentServiceModel> departmentServiceModel;

    @CreationTimestamp
    private LocalTime createdAt;
    @UpdateTimestamp
    private LocalTime updatedAt;
}
