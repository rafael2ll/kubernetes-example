package com.demo.catalog.api.advice;

import com.example.demo.exception.BusinessError;
import com.example.demo.exception.ConflictError;
import com.example.demo.exception.NotFoundError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorInterceptor {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleValidationErrors(MethodArgumentNotValidException ex) {
        var errors = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(errors.stream().map(ErrorResponse::new).toList());
    }

    @ExceptionHandler(ConflictError.class)
    public ResponseEntity handleConflictError(ConflictError ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse(ex));
    }

    @ExceptionHandler(BusinessError.class)
    public ResponseEntity handleBusinessError(BusinessError ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(ex));
    }

    @ExceptionHandler(NotFoundError.class)
    public ResponseEntity handleNotFoundError(NotFoundError ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(ex));
    }
}
