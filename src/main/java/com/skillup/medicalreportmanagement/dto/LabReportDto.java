package com.skillup.medicalreportmanagement.dto;

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
public class LabReportDto {

    private Integer labReportId;
    @NotNull(message = "Lab appointment id cannot be null")
    private Integer labAppointmentId;
    @NotBlank(message = "Lab test results cannot be empty")
    private String labTestResults;
}
