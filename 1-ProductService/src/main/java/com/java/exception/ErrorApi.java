package com.java.exception;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ErrorApi {
    private String message;
    private String status;
    private String error;
    private LocalDateTime localDateTime =  LocalDateTime.now();
}
