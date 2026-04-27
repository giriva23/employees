package com.etica.employees.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ApiResponseDTO <T> {
    private int status;
    private String message;
    private Boolean success;
    private T data;

    public ApiResponseDTO(HttpStatus httpStatus, String message, T data) {
        this.status = httpStatus.value();
        this.message = message;
        this.success = true;
        this.data = data;
    }


}
