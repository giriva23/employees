package com.etica.employees.web.controller;

import com.etica.employees.model.Employee;
import com.etica.employees.service.EmployeeService;
import com.etica.employees.web.dto.ApiResponseDTO;
import com.etica.employees.web.dto.EmployeeDTO;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService service) {
        this.employeeService = service;
    }

    @GetMapping
    public ResponseEntity<ApiResponseDTO<?>> getAll() {
        List<Employee> employees = employeeService.getAll();
        return ResponseEntity.ok(
                new ApiResponseDTO<>(HttpStatus.OK, "Empleados obtenidos exitosamente.", employees)
        );
    }


    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<?>> getById(@PathVariable Long id) {
        Employee employee = employeeService.getById(id);
        return ResponseEntity.ok(
                new ApiResponseDTO<>(HttpStatus.OK, "Empleado obtenido exitosamente.", employee)
        );
    }

    @PostMapping
    public ResponseEntity<ApiResponseDTO<?>> create(@Valid @RequestBody EmployeeDTO dto) {
        Employee created = employeeService.create(dto);
        return ResponseEntity.ok(
                new ApiResponseDTO<>(HttpStatus.CREATED, "Empleado creado exitosamente.", created)
        );
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponseDTO<List<Employee>>> getByPosition(@RequestParam String position) {
        List<Employee> employees = employeeService.getByPosition(position);
        return ResponseEntity.ok(
                new ApiResponseDTO<>(HttpStatus.OK, "Empleado obtenido exitosamente.", employees)
        );
    }
}