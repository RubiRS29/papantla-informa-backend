package com.ayunyamiento.papantla.papantla_informa.models.dtos;

import com.ayunyamiento.papantla.papantla_informa.models.EmployeeModel;
import com.ayunyamiento.papantla.papantla_informa.models.Priority;
import com.ayunyamiento.papantla.papantla_informa.models.Tag;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalTime;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
public class NewsDTO {

    private Long newsId;
    private String title;
    private String description;
    private EmployeeModel employeeCreator;
    private Boolean isPublic = true;
    private Boolean active = false;
    private String imagePath;
    private Boolean needNotification = false;
    private Tag newsTag;
    private Priority priority = Priority.NONE;

    private LocalTime createdAt;
    private LocalTime updatedAt;
}
