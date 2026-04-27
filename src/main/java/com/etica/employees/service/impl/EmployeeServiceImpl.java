package com.etica.employees.service.impl;

import com.etica.employees.exception.ResourceNotFoundException;
import com.etica.employees.model.Employee;
import com.etica.employees.repository.EmployeeRepository;
import com.etica.employees.service.EmployeeService;
import com.etica.employees.web.dto.EmployeeDTO;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repository;

    @PostConstruct
    public void init() {
        if (repository.count() == 0) {
            repository.saveAll(List.of(
                    new Employee(null, "María Ana Alfonso", "Contador", 3000000.0),
                    new Employee(null, "Paz Valentina Portillo", "Diseñador UX", 4500000.0),
                    new Employee(null, "Carlos Saúl Acosta", "Proyect Manager", 4000000.0)
            ));
        }
    }

    @Override
    public List<Employee> getAll() {
        log.info("Listando empleados.");
        return repository.findAll();
    }

    @Override
    public Employee getById(Long id) {
        log.info("Obteniendo empleado. ID: {}", id);
        return repository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Empleado no encontrado. ID: {}", id);
                    return new ResourceNotFoundException(
                            "No se encontró el empleado solicitado",
                            "EMPLOYEE_NOT_FOUND"
                    );
                });
    }

    @Override
    public Employee create(EmployeeDTO dto) {
        log.info("Iniciando creación de empleado. Nombre: {}, Puesto: {}", dto.getName(), dto.getPosition());
        Employee emp = new Employee();
        emp.setName(dto.getName());
        emp.setPosition(dto.getPosition());
        emp.setSalary(dto.getSalary());

        repository.save(emp);
        log.info("Empleado creado exitosamente. ID: {}", emp.getId());
        return emp;
    }

    @Override
    public List<Employee> getByPosition(String position) {
        log.info("Buscando empleados. Posición: {}", position);
        List<Employee> employees = repository.findByPositionIgnoreCase(position);
        if (employees.isEmpty()) {
            log.warn("Empleado no encontrado. Posición: {}", position);
            throw new ResourceNotFoundException(
                    "No se encontraron empleados para ese puesto.",
                    "EMPLOYEE_NOT_FOUND");
        }
        return employees;
    }

}
