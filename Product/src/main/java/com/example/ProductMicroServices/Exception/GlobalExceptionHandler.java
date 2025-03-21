package com.example.ProductMicroServices.Exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleProductNotFoundException(ProductNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoSuchBrandException.class)
    public ResponseEntity<ErrorResponse> handleNoSuchBrandException(NoSuchBrandException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoSuchCategoryException.class)
    public ResponseEntity<ErrorResponse> handleNoSuchCategoryException(NoSuchCategoryException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UnauthorizedAccess.class)
    public ResponseEntity<ErrorResponse> handleUnauthorizedAccessException(UnauthorizedAccess ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), HttpStatus.UNAUTHORIZED.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);

    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    public ResponseEntity<ErrorResponse> handleHttpMediaTypeNotAcceptableException(HttpMediaTypeNotAcceptableException ex) {
        ErrorResponse errorResponse = new ErrorResponse("No acceptable representation found.", HttpStatus.NOT_ACCEPTABLE.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_ACCEPTABLE);
    }
    public static class ErrorResponse {
        private final String message;
        private final int status;

        public ErrorResponse(String message, int status) {
            this.message = message;
            this.status = status;
        }

        public String getMessage(){
            return message;
        }
        public int getStatus(){
            return status;
        }


    }
}