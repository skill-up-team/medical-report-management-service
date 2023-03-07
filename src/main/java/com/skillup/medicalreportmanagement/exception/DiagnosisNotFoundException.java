package com.skillup.medicalreportmanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DiagnosisNotFoundException extends RuntimeException{

    public DiagnosisNotFoundException(String message) {
        super(message);
    }
}
