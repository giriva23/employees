package com.etica.employees.exception;

import lombok.Data;

import java.util.Map;

@Data
public class ErrorResponse {
    private int status;
    private String message;
    private Boolean success;
    private String code;
    private Map<String, String> errors;

    public ErrorResponse(int status, String message, String code) {
        this.status = status;
        this.message = message;
        this.success = false;
        this.code = code;
    }
    public ErrorResponse(int status, String message, String code, Map<String, String> errors) {
        this(status, message, code);
        this.errors = errors;
    }
}
