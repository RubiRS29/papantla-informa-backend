package com.ayunyamiento.papantla.papantla_informa.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalTime;

@Entity(name = "news")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class NewsModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long newsId;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String description;
    @ManyToOne(fetch = FetchType.LAZY)
    private EmployeeModel employeeCreator;

    private Boolean isPublic = true;
    private Boolean active = false;
    @Column(nullable = true)
    private String imagePath;

    private Boolean needNotification = false;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Tag newsTag;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Priority priority = Priority.LOW;


    @CreationTimestamp
    private LocalTime createdAt;
    @UpdateTimestamp
    private LocalTime updatedAt;
}
