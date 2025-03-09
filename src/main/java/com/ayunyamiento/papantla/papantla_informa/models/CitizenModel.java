package com.ayunyamiento.papantla.papantla_informa.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalTime;

@Entity(name = "citizen")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CitizenModel extends UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long citizenId;

    @CreationTimestamp
    private LocalTime createdAt;
    @UpdateTimestamp
    private LocalTime updatedAt;

}

