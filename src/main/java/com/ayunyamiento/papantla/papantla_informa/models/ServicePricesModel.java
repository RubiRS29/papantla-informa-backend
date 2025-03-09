package com.ayunyamiento.papantla.papantla_informa.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name = "prices")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ServicePricesModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long priceId;

    @Column(nullable = false)
    private String identifyWord;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_id", nullable = false)
    private DepartmentServiceModel service;

    @Column(nullable = false)
    private String daysOfWeek;
    @Column(nullable = false)
    private Double price;
    @Column(nullable = true)
    private String notes;


}
