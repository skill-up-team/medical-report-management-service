package com.skillup.medicalreportmanagement.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.List;

@Log4j2
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse = ExceptionResponse.builder().timestamp(LocalDateTime.now())
                .message(ex.getMessage()).details(request.getDescription(false))
                .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                .build();

        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(EntryNotFoundException.class)
    public final ResponseEntity<Object> handleDiagnosisNotFoundException(EntryNotFoundException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = ExceptionResponse.builder().timestamp(LocalDateTime.now())
                .message(ex.getMessage()).details(request.getDescription(false))
                .httpStatus(HttpStatus.NOT_FOUND)
                .build();

        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EntryExistException.class)
    public final ResponseEntity<Object> handleDiagnosisExistException(EntryExistException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = ExceptionResponse.builder().timestamp(LocalDateTime.now())
                .message(ex.getMessage()).details(request.getDescription(false))
                .httpStatus(HttpStatus.CONFLICT)
                .build();

        return new ResponseEntity<>(exceptionResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final ResponseEntity<Object> handleInvalidArgument(MethodArgumentNotValidException ex, WebRequest request) {
        List<ErrorField> errorList = ex.getBindingResult().getFieldErrors().stream().map(
                        fieldError -> ErrorField.builder()
                                .fieldName(fieldError.getField())
                                .message(fieldError.getDefaultMessage())
                                .build())
                                .toList();

        ExceptionResponse exceptionResponse = ExceptionResponse.builder().timestamp(LocalDateTime.now())
                .message(ex.getMessage()).details(request.getDescription(false))
                .fieldErrors(errorList)
                .httpStatus(HttpStatus.BAD_REQUEST)
                .build();

        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

}
