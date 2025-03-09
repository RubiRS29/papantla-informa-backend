package com.ayunyamiento.papantla.papantla_informa.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalTime;

@Entity(name = "employee")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeModel extends UserModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long employeeId;

    @Column(nullable = false)
    private String employeePrivateId;
    @Column(nullable = false)
    private String position;
    @Column(nullable = false)
    private String department;
    @Column(nullable = false)
    private Rol role;

    @CreationTimestamp
    private LocalTime createdAt;
    @UpdateTimestamp
    private LocalTime updatedAt;
}

