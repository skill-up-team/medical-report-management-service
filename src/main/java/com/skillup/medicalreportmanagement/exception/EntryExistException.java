package com.skillup.medicalreportmanagement.exception;

public class EntryExistException extends RuntimeException{
    public EntryExistException(String message) {
        super(message);
    }
}
