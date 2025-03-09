package com.ayunyamiento.papantla.papantla_informa.models.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class DepartmentServiceDTO {
    private Long serviceId;
    private String identifyWord;
    private String serviceName;
    private String serviceDescription;
    private Boolean status;
    @JsonIgnore
    private DepartmentDTO departmentDTO;
    private List<RequirementsDTO> requirementsDTOS;
    private List<ServicePricesDTO> servicePricesDTOS;
}
