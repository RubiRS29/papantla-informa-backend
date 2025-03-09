package com.ayunyamiento.papantla.papantla_informa.models.dtos;

import com.ayunyamiento.papantla.papantla_informa.models.EmployeeModel;
import com.ayunyamiento.papantla.papantla_informa.models.Priority;
import com.ayunyamiento.papantla.papantla_informa.models.Tag;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.time.LocalTime;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EventsDTO {

    private Long eventId;
    private String eventName;
    private String eventDescription;
    private String eventLocation;
    private String eventImg;
    private LocalDateTime eventDate;
    private LocalDateTime endEventDate;
    private Tag eventTag;
    private Priority priority = Priority.LOW;
    private EmployeeModel employee;
    private Boolean isPublic = true;
    private Boolean status = false;

    private LocalTime createdAt;
    private LocalTime updatedAt;
}
