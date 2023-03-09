package com.skillup.medicalreportmanagement.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class ExceptionResponse {
    private LocalDateTime timestamp;
    private String message;
    private String details;
    private HttpStatus httpStatus;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<ErrorField> fieldErrors;
}
