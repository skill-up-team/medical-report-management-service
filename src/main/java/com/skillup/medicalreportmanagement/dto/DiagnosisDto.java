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
public class DiagnosisDto {
    public interface Create {}
    public interface Update {}

    @NotNull(message = "Diagnosis id cannot be null", groups = {Update.class})
    private Integer diagnosisId;
    @NotBlank(message = "Description cannot be empty", groups = {Create.class, Update.class})
    private String diagnosisDescription;
    @NotNull(message = "Doctor appointment cannot be null", groups = {Create.class, Update.class})
    private Integer doctorAppointmentId;
}
