package com.demo.catalog.api.advice;

import com.example.demo.exception.BusinessError;
import org.springframework.validation.FieldError;

import java.util.Optional;

public record ErrorResponse(Optional<String> campo, String message) {
    public ErrorResponse(FieldError error) {
        this(Optional.of(error.getField()), error.getDefaultMessage());
    }
    public ErrorResponse(BusinessError error) {
        this(Optional.empty(), error.getMessage());
    }
}