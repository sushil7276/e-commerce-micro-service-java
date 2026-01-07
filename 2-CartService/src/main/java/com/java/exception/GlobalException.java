package com.java.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalException {


    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorApi> exceptionHandler(UserNotFoundException ex) {
        log.info("User Not Found {}", ex.getMessage());

        ErrorApi errorApi = new ErrorApi();
        errorApi.setMessage(ex.getMessage());
        errorApi.setError("User Not Found");
        errorApi.setStatus(HttpStatus.BAD_REQUEST.getReasonPhrase());

        return new ResponseEntity<>(errorApi, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorApi> exceptionHandler(ProductNotFoundException ex) {
        log.info("Product Not Found {}", ex.getMessage());

        ErrorApi errorApi = new ErrorApi();
        errorApi.setMessage(ex.getMessage());
        errorApi.setError("Product Not Found");
        errorApi.setStatus(HttpStatus.BAD_REQUEST.getReasonPhrase());

        return new ResponseEntity<>(errorApi, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorApi> exceptionHandler(Exception ex) {
        log.info("Internal Error {}", ex.getMessage());

        ErrorApi errorApi = new ErrorApi();
        errorApi.setMessage(ex.getMessage());
        errorApi.setError("Something went wrong. Please try again later");
        errorApi.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());

        return new ResponseEntity<>(errorApi, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
