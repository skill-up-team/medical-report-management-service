package com.skillup.medicalreportmanagement.mapper;

import com.skillup.medicalreportmanagement.dto.DiagnosisDto;
import com.skillup.medicalreportmanagement.dto.LabReportDto;
import com.skillup.medicalreportmanagement.entity.Diagnosis;
import com.skillup.medicalreportmanagement.entity.LabReport;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EntityDtoMapper {

    @Mapping(target="diagnosisId", source="entity.id")
    @Mapping(target="diagnosisDescription", source="entity.description")
    DiagnosisDto diagnosisToDiagnosisDTO(Diagnosis entity);

    @Mapping(target="id", source="dto.diagnosisId")
    @Mapping(target="description", source="dto.diagnosisDescription")
    Diagnosis diagnosisDTOtoDiagnosis(DiagnosisDto dto);

    @Mapping(target="labReportId", source="entity.id")
    @Mapping(target="labTestResults", source="entity.testResults")
    LabReportDto labReportToLabReportDTO(LabReport entity);

    @Mapping(target="id", source="dto.labReportId")
    @Mapping(target="testResults", source="dto.labTestResults")
    LabReport labReportDTOtoLabReport(LabReportDto dto);

}
