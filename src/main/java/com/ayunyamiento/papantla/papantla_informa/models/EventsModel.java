package com.ayunyamiento.papantla.papantla_informa.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity(name = "event")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class EventsModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long eventId;

    @Column(nullable = false)
    private String eventName;
    @Column(nullable = false)
    private String eventDescription;
    @Column(nullable = true)
    private String eventLocation;
    @Column(nullable = true)
    private String eventImg;
    @Column(nullable = false)
    private LocalDateTime eventDate;

    @Column(nullable = false)
    private LocalDateTime endEventDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Tag eventTag;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Priority priority = Priority.LOW;

    @ManyToOne(fetch = FetchType.LAZY)
    private EmployeeModel employee;

    private Boolean isPublic = true;
    private Boolean status = false;

    @CreationTimestamp
    private LocalTime createdAt;
    @UpdateTimestamp
    private LocalTime updatedAt;
}
