package com.skillup.medicalreportmanagement.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ErrorField {

    private String fieldName;
    private String message;
}
