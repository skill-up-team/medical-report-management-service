package com.skillup.medicalreportmanagement.dto;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiagnosisDto {

    private Integer diagnosisId;
    @NotBlank(message = "Description cannot be empty")
    private String diagnosisDescription;
    @NotNull(message = "Doctor appointment cannot be null")
    private Integer doctorAppointmentId;
}
