package com.etica.employees.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class EmployeeDTO {
    @NotBlank(message = "Debe ingresar el nombre del empleado.")
    private String name;
    @NotBlank(message = "Debe ingresar el puesto del empleado.")
    private String position;
    @NotNull(message = "Debe ingresar el salario del empleado.")
    @Positive(message = "El salario debe ser mayor a cero.")
    private Double salary;
}
