package com.etica.employees.repository;

import com.etica.employees.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByPositionIgnoreCase(String position);
}
