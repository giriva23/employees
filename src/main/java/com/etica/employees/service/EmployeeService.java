package com.etica.employees.service;

import com.etica.employees.model.Employee;
import com.etica.employees.web.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAll();
    Employee getById(Long id);
    Employee create(EmployeeDTO dto);
    List<Employee> getByPosition(String position);
}
