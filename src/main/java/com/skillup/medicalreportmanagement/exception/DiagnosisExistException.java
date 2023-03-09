package com.skillup.medicalreportmanagement.exception;

public class DiagnosisExistException extends RuntimeException{
    public DiagnosisExistException(String message) {
        super(message);
    }
}
