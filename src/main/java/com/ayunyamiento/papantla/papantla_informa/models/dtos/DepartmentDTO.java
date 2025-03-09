package com.ayunyamiento.papantla.papantla_informa.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class DepartmentDTO {

    private Long departmentId;
    private String departmentIdentify;
    private String departmentName;
    private String departmentDescription;
    private String headOfDepartment;
    private String contactEmail;
    private String contactPhone;
    private String address;

    private List<DepartmentServiceDTO> servicesDto;

}
