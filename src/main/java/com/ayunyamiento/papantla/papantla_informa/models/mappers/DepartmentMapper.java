package com.ayunyamiento.papantla.papantla_informa.models.mappers;

import com.ayunyamiento.papantla.papantla_informa.models.*;
import com.ayunyamiento.papantla.papantla_informa.models.dtos.DepartmentDTO;
import com.ayunyamiento.papantla.papantla_informa.models.dtos.DepartmentServiceDTO;
import com.ayunyamiento.papantla.papantla_informa.models.dtos.RequirementsDTO;
import com.ayunyamiento.papantla.papantla_informa.models.dtos.ServicePricesDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class DepartmentMapper {

    public static DepartmentModel mapperDepartmentModel(DepartmentDTO departmentDTO) {

        DepartmentModel departmentModel = new DepartmentModel();
        departmentModel.setDepartmentId(departmentDTO.getDepartmentId());
        departmentModel.setDepartmentName(departmentDTO.getDepartmentName());
        departmentModel.setDepartmentDescription(departmentDTO.getDepartmentDescription());
        departmentModel.setHeadOfDepartment(departmentDTO.getHeadOfDepartment());
        departmentModel.setContactEmail(departmentDTO.getContactEmail());
        departmentModel.setContactPhone(departmentDTO.getContactPhone());
        departmentModel.setAddress(departmentDTO.getAddress());
        departmentModel.setDepartmentIdentify(departmentDTO.getDepartmentIdentify());

// 🔹 Convertir servicios manualmente
        List<DepartmentServiceModel> serviceModels = new ArrayList<>();
        if (departmentDTO.getServicesDto() != null) {
            for (DepartmentServiceDTO serviceDTO : departmentDTO.getServicesDto()) {
                DepartmentServiceModel serviceModel = new DepartmentServiceModel();
                serviceModel.setIdentifyWord(serviceDTO.getIdentifyWord());
                serviceModel.setServiceName(serviceDTO.getServiceName());
                serviceModel.setServiceDescription(serviceDTO.getServiceDescription());
                serviceModel.setStatus(serviceDTO.getStatus());
                serviceModel.setDepartmentModel(departmentModel);

                // 🔹 Convertir requisitos
                List<RequirementsModel> requirementsModels = new ArrayList<>();
                if (serviceDTO.getRequirementsDTOS() != null) {
                    for (RequirementsDTO reqDTO : serviceDTO.getRequirementsDTOS()) {
                        RequirementsModel reqModel = new RequirementsModel();
                        reqModel.setIdentifyWord(reqDTO.getIdentifyWord());
                        reqModel.setRequirementName(reqDTO.getRequirementName());
                        reqModel.setRequirementDescription(reqDTO.getRequirementDescription());
                        reqModel.setCopies(reqDTO.getCopies());
                        reqModel.setIsMandatory(reqDTO.getIsMandatory());
                        reqModel.setNotes(reqDTO.getNotes());
                        reqModel.setService(serviceModel); // 🔥 Relación de regreso
                        requirementsModels.add(reqModel);
                    }
                }
                serviceModel.setRequirementsModels(requirementsModels);

                // 🔹 Convertir precios
                List<ServicePricesModel> priceModels = new ArrayList<>();
                if (serviceDTO.getServicePricesDTOS() != null) {
                    for (ServicePricesDTO priceDTO : serviceDTO.getServicePricesDTOS()) {
                        ServicePricesModel priceModel = new ServicePricesModel();
                        priceModel.setIdentifyWord(priceDTO.getIdentifyWord());
                        priceModel.setPrice(priceDTO.getPrice());
                        priceModel.setNotes(priceDTO.getNotes());
                        priceModel.setDaysOfWeek(priceDTO.getDaysOfWeek()
                                .stream()
                                .map(Enum::name)
                                .collect(Collectors.joining(",")));
                        priceModel.setService(serviceModel); // 🔥 Relación de regreso
                        priceModels.add(priceModel);
                    }
                }
                serviceModel.setServicePricesModels(priceModels);

                serviceModels.add(serviceModel);
            }
        }

        departmentModel.setDepartmentServiceModel(serviceModels);

        return departmentModel;
    }

    public static DepartmentDTO mapperDepartmentDto(DepartmentModel departmentModel) {
        DepartmentDTO departmentDto = new DepartmentDTO();

        departmentDto.setDepartmentId(departmentModel.getDepartmentId());
        departmentDto.setDepartmentName(departmentModel.getDepartmentName());
        departmentDto.setDepartmentDescription(departmentModel.getDepartmentDescription());
        departmentDto.setHeadOfDepartment(departmentModel.getHeadOfDepartment());
        departmentDto.setContactEmail(departmentModel.getContactEmail());
        departmentDto.setContactPhone(departmentModel.getContactPhone());
        departmentDto.setAddress(departmentModel.getAddress());
        departmentDto.setDepartmentIdentify(departmentModel.getDepartmentIdentify());

        // 🔹 Convertir servicios
        List<DepartmentServiceDTO> services = new ArrayList<>();
        if (departmentModel.getDepartmentServiceModel() != null) {
            for (DepartmentServiceModel serviceModel : departmentModel.getDepartmentServiceModel()) {
                DepartmentServiceDTO serviceDTO = new DepartmentServiceDTO();
                serviceDTO.setServiceId(serviceModel.getServiceId());
                serviceDTO.setIdentifyWord(serviceModel.getIdentifyWord());
                serviceDTO.setServiceName(serviceModel.getServiceName());
                serviceDTO.setServiceDescription(serviceModel.getServiceDescription());
                serviceDTO.setStatus(serviceModel.getStatus());
                serviceDTO.setDepartmentDTO(departmentDto);

                // 🔹 Convertir requisitos
                List<RequirementsDTO> requirements = new ArrayList<>();
                if (serviceModel.getRequirementsModels() != null) {
                    for (RequirementsModel reqModel : serviceModel.getRequirementsModels()) {
                        RequirementsDTO reqDto = new RequirementsDTO();
                        reqDto.setRequirementId(reqModel.getRequirementId());
                        reqDto.setIdentifyWord(reqModel.getIdentifyWord());
                        reqDto.setRequirementName(reqModel.getRequirementName());
                        reqDto.setRequirementDescription(reqModel.getRequirementDescription());
                        reqDto.setCopies(reqModel.getCopies());
                        reqDto.setIsMandatory(reqModel.getIsMandatory());
                        reqDto.setNotes(reqModel.getNotes());
                        reqDto.setServiceDTO(serviceDTO); // 🔥 Relación de regreso
                        requirements.add(reqDto);
                    }
                }
                serviceDTO.setRequirementsDTOS(requirements);

                // 🔹 Convertir precios
                List<ServicePricesDTO> prices = new ArrayList<>();
                if (serviceModel.getServicePricesModels() != null) {
                    for (ServicePricesModel priceModel : serviceModel.getServicePricesModels()) {
                        ServicePricesDTO priceDto = new ServicePricesDTO();
                        priceDto.setPriceId(priceModel.getPriceId());
                        priceDto.setIdentifyWord(priceModel.getIdentifyWord());
                        priceDto.setPrice(priceModel.getPrice());
                        priceDto.setNotes(priceModel.getNotes());
                        priceDto.setDaysOfWeek(getDaysOfWeek(priceModel.getDaysOfWeek()));
                        priceDto.setServiceDTO(serviceDTO); // 🔥 Relación de regreso
                        prices.add(priceDto);
                    }
                }
                serviceDTO.setServicePricesDTOS(prices);

                services.add(serviceDTO);
            }
        }
        departmentDto.setServicesDto(services);

        return departmentDto;
    }

    public static List<DayOfWeek> getDaysOfWeek(String daysOfWeek) {
        return Arrays.stream(daysOfWeek.split(","))
                .map(String::trim)
                .map(DepartmentMapper::fromSpanishToEnum)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public static DayOfWeek fromSpanishToEnum(String dayInSpanish) {
        for (DayOfWeek day : DayOfWeek.values()) {
            if (day.dayName.equalsIgnoreCase(dayInSpanish)) {
                return day;
            }
        }
        return null;
    }

}
