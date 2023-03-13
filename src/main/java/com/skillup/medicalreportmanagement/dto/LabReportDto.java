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

    public interface Create {}
    public interface Update {}

    @NotNull(message = "Lab report id cannot be null",groups = {Update.class})
    private Integer labReportId;
    @NotNull(message = "Lab appointment id cannot be null", groups = {Create.class, Update.class})
    private Integer labAppointmentId;
    @NotBlank(message = "Lab test results cannot be empty", groups = {Create.class, Update.class})
    private String labTestResults;
}
