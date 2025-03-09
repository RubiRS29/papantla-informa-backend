package com.ayunyamiento.papantla.papantla_informa.controllers;


import com.ayunyamiento.papantla.papantla_informa.models.dtos.DepartmentDTO;
import com.ayunyamiento.papantla.papantla_informa.services.MunicipalServicesManager;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/departamentos")
public class MunicipalControllerManager {

    private final MunicipalServicesManager municipalServicesManager;

    public MunicipalControllerManager(MunicipalServicesManager municipalServicesManager) {
        this.municipalServicesManager = municipalServicesManager;
    }

    @PostMapping("/crear")
    public ResponseEntity<DepartmentDTO> createMunicipalDepartment(@RequestParam("file") MultipartFile file) throws IOException {
       return ResponseEntity.ok().body(municipalServicesManager.readXSLSToCreateMunicipalServices(file));
    }

    @PatchMapping("/actualizar")
    public ResponseEntity<DepartmentDTO> updateMunicipalDepartment(@RequestParam("file") MultipartFile file) throws IOException {
        return ResponseEntity.ok().body(municipalServicesManager.readXSLSToUpdateMunicipalServices(file));
    }

    @GetMapping("/departamentos")
    public ResponseEntity<List<DepartmentDTO>> getMunicipalDepartments() throws IOException {
        return ResponseEntity.ok().body(municipalServicesManager.getDepartments());
    }

    @GetMapping("/departamentos/{id}")
    public ResponseEntity<DepartmentDTO> getMunicipalDepartmentById(
            @PathVariable(required = true) Long id ) throws IOException {
        return ResponseEntity.ok().body(municipalServicesManager.getDepartments(id));
    }

}
