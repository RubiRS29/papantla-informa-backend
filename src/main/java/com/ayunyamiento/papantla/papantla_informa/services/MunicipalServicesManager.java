package com.ayunyamiento.papantla.papantla_informa.services;

import com.ayunyamiento.papantla.papantla_informa.models.mappers.DepartmentMapper;
import com.ayunyamiento.papantla.papantla_informa.models.*;
import com.ayunyamiento.papantla.papantla_informa.models.dtos.DepartmentDTO;
import com.ayunyamiento.papantla.papantla_informa.models.dtos.DepartmentServiceDTO;
import com.ayunyamiento.papantla.papantla_informa.models.dtos.RequirementsDTO;
import com.ayunyamiento.papantla.papantla_informa.models.dtos.ServicePricesDTO;
import com.ayunyamiento.papantla.papantla_informa.repositories.DepartmentRepository;
import com.ayunyamiento.papantla.papantla_informa.repositories.DepartmentServiceRepository;
import com.ayunyamiento.papantla.papantla_informa.repositories.RequirementsRepository;
import jakarta.persistence.EntityNotFoundException;
import org.apache.coyote.BadRequestException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import static com.ayunyamiento.papantla.papantla_informa.models.mappers.DepartmentMapper.*;


@Service
public class MunicipalServicesManager {

    private final DepartmentRepository departmentRepository;

    public MunicipalServicesManager(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    /**
     * Method to create a Department from an XLSX file.
     *
     * This method reads data from an Excel file, which must contain exactly four sheets with the following names:
     * - "area"       → Represents the department.
     * - "servicios"  → Lists the services offered by the department.
     * - "requisitos" → Defines the requirements for each service.
     * - "precios"    → Specifies the prices for each service.
     * servicios, requisitos and precios needs a reference word, for make a relationship into the xlsx file 
     * The method processes each sheet accordingly and maps the extracted data into a Department model.
     */
    public DepartmentDTO readXSLSToCreateMunicipalServices(MultipartFile xlsx) throws IOException {

        InputStream file = xlsx.getInputStream();
        DepartmentDTO departmentDTO = readXlsxGettingDepartments(file);

        // validation for no duplicate keys creating a department
        Optional<DepartmentModel> byDepartmentIdentify = departmentRepository
                .findByIdWithServices(departmentDTO.getDepartmentIdentify());

        if (byDepartmentIdentify.isPresent()) {
            throw new DuplicateKeyException("El departamento existe con el identificador: " + departmentDTO.getDepartmentIdentify());
        }

        DepartmentModel model = departmentRepository.save(mapperDepartmentModel(departmentDTO));

        return mapperDepartmentDto(model);


    }

    public DepartmentDTO readXSLSToUpdateMunicipalServices(MultipartFile xlsx) throws IOException {

        InputStream file = xlsx.getInputStream();
        DepartmentDTO departmentDTO = readXlsxGettingDepartments(file);

        // validation for no duplicate keys creating a department
        Optional<DepartmentModel> byDepartmentIdentify = departmentRepository
                .findByIdWithServices(departmentDTO.getDepartmentIdentify());

        if(byDepartmentIdentify.isEmpty()) {
            throw new EntityNotFoundException("El departamento no existe");
        }
        departmentDTO.setDepartmentId(byDepartmentIdentify.get().getDepartmentId());
        DepartmentModel model = departmentRepository.save(mapperDepartmentModel(departmentDTO));

        return mapperDepartmentDto(model);

    }

    public List<DepartmentDTO> getDepartments() {

        List<DepartmentModel> departmentModelList = departmentRepository.findAll();
        return departmentModelList
                .stream()
                .map(DepartmentMapper::mapperDepartmentDto)
                .collect(Collectors.toList());
        
    }

    public DepartmentDTO getDepartments(Long id) {
        Optional<DepartmentModel> depto = departmentRepository.findById(id);
        if (depto.isPresent()) {
            return mapperDepartmentDto(depto.get());
        }
        throw new EntityNotFoundException(String.format("Department with id %s not found", id));
    }

    private DepartmentDTO readXlsxGettingDepartments(InputStream file) throws IOException {
        DepartmentDTO departmentDTO = new DepartmentDTO();
        List<DepartmentServiceDTO> servicesDto = new ArrayList<>();

        try {
             Workbook workbook = new XSSFWorkbook(file);
            Iterator<Sheet> sheetIterator = workbook.sheetIterator();

            while (sheetIterator.hasNext()) {
                Sheet sheet = sheetIterator.next();
                switch (sheet.getSheetName()) {
                    case "area":
                        departmentDTO = getDepartmentBySheet(sheet);
                        break;
                    case "servicios":
                        departmentDTO.setServicesDto(getServicesBySheet(sheet));
                        break;
                    case "requisitos":
                        List<RequirementsDTO> requirements = getRequirementsBySheet(sheet);
                        servicesDto = departmentDTO.getServicesDto();
                        servicesDto.forEach(svrs ->
                                svrs.setRequirementsDTOS(requirements
                                        .stream()
                                        .filter(req -> req.getIdentifyWord().equals(svrs.getIdentifyWord())).toList()));
                        break;
                    case "precios":
                        List<ServicePricesDTO> servicePrices = getServicePricesBySheet(sheet);
                        servicesDto = departmentDTO.getServicesDto();
                        servicesDto.forEach(svr ->
                                svr.setServicePricesDTOS(servicePrices
                                        .stream()
                                        .filter(price -> price.getIdentifyWord().equals(svr.getIdentifyWord())).toList()));
                        break;
                    default:
                        break;
                }
            }
            return departmentDTO;
        } catch (Exception e){
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

    }

    private DepartmentDTO getDepartmentBySheet(Sheet sheet) throws BadRequestException {
        DepartmentDTO department = new DepartmentDTO();

        if(sheet.getSheetName().equals("area")){

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);

                if (row != null ) {
                    if (isRowEmpty(row)) {
                        continue;
                    }
                    department.setDepartmentName(getCellValue(row.getCell(0)));
                    department.setDepartmentDescription(getCellValue(row.getCell(1)));
                    department.setHeadOfDepartment(getCellValue(row.getCell(2)));
                    department.setContactEmail(getCellValue(row.getCell(3)));
                    department.setContactPhone(getCellValue(row.getCell(4)));
                    department.setAddress(getCellValue(row.getCell(5)));
                    department.setDepartmentIdentify(getCellValue(row.getCell(6)));

                }
            }
        }

        return department;
    }

    private List<DepartmentServiceDTO> getServicesBySheet(Sheet sheet) {
        List<DepartmentServiceDTO>  serviceDTOS = new ArrayList<>();

        if(sheet.getSheetName().equals("servicios")){
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row != null ) {
                    if (isRowEmpty(row)) {
                        continue;
                    }
                    DepartmentServiceDTO service = new DepartmentServiceDTO();
                    service.setIdentifyWord(getCellValue(row.getCell(0)));
                    service.setServiceName(getCellValue(row.getCell(1)));
                    service.setServiceDescription(getCellValue(row.getCell(2)));
                    service.setStatus(getCellValue(row.getCell(2)).equals("si"));

                    serviceDTOS.add(service);
                }
            }
        }
        return serviceDTOS;
    }

    private List<RequirementsDTO> getRequirementsBySheet(Sheet sheet) {
        List<RequirementsDTO>  requirementsDTOS = new ArrayList<>();

        if(sheet.getSheetName().equals("requisitos")){
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row != null ) {
                    if (isRowEmpty(row)) {
                        continue;
                    }
                    RequirementsDTO requirementDTO = new RequirementsDTO();
                    requirementDTO.setIdentifyWord(getCellValue(row.getCell(0)));
                    requirementDTO.setRequirementName(getCellValue(row.getCell(1)));
                    requirementDTO.setRequirementDescription(getCellValue(row.getCell(2)));
                    requirementDTO.setCopies(Integer.parseInt(getCellValue(row.getCell(3))));
                    requirementDTO.setIsMandatory(getCellValue(row.getCell(4)).equals("si"));
                    requirementDTO.setNotes(getCellValue(row.getCell(5)));

                    requirementsDTOS.add(requirementDTO);
                }
            }
        }
        return requirementsDTOS;
    }

    private List<ServicePricesDTO> getServicePricesBySheet(Sheet sheet) {
        List<ServicePricesDTO>  servicePricesDTOS = new ArrayList<>();

        if(sheet.getSheetName().equals("precios")){
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row != null ) {
                    if (isRowEmpty(row)) {
                        continue;
                    }
                    ServicePricesDTO servicePricesDTO = new ServicePricesDTO();
                    servicePricesDTO.setIdentifyWord(getCellValue(row.getCell(0)));
                    servicePricesDTO.setDaysOfWeek(getDaysOfWeek(getCellValue(row.getCell(1))));
                    servicePricesDTO.setPrice(Double.parseDouble(getCellValue(row.getCell(2))));
                    servicePricesDTO.setNotes(getCellValue(row.getCell(3)));
                    //add the price to the list
                    servicePricesDTOS.add(servicePricesDTO);

                }
            }
        }
        return servicePricesDTOS;
    }

    private static String getCellValue(Cell cell) {
        if (cell == null) {
            return "";
        }

        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                } else {
                    return String.valueOf((long) cell.getNumericCellValue());  // Convertir a long para evitar decimales en números enteros
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            default:
                return "";
        }
    }

    private static boolean isRowEmpty(Row row) {
        boolean isEmpty = true;
        DataFormatter dataFormatter = new DataFormatter();

        if (row != null) {
            for (Cell cell : row) {
                if (dataFormatter.formatCellValue(cell).trim().length() > 0) {
                    isEmpty = false;
                    break;
                }
            }
        }

        return isEmpty;
    }

}
