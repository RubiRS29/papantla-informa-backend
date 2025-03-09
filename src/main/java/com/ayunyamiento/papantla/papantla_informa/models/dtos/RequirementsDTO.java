package com.ayunyamiento.papantla.papantla_informa.models.dtos;

import com.ayunyamiento.papantla.papantla_informa.models.DepartmentServiceModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequirementsDTO {
    private Long requirementId;
    private String identifyWord;
    private String requirementName;
    private String requirementDescription;
    private int copies;
    private Boolean isMandatory;
    private String notes;
    @JsonIgnore
    private DepartmentServiceDTO serviceDTO;

}
