package com.ayunyamiento.papantla.papantla_informa.models.dtos;

import com.ayunyamiento.papantla.papantla_informa.models.DayOfWeek;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.List;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ServicePricesDTO {
    private Long priceId;
    private String identifyWord;
    @JsonIgnore
    private DepartmentServiceDTO serviceDTO;
    private List<DayOfWeek> daysOfWeek;
    private Double price;
    private String notes;
}
