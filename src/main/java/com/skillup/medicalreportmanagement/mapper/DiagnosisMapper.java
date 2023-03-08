package com.skillup.medicalreportmanagement.mapper;

import com.skillup.medicalreportmanagement.dto.DiagnosisDto;
import com.skillup.medicalreportmanagement.entity.Diagnosis;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DiagnosisMapper {

    @Mapping(target="diagnosisId", source="entity.id")
    @Mapping(target="diagnosisDescription", source="entity.description")
    DiagnosisDto diagnosisToDiagnosisDTO(Diagnosis entity);

    @Mapping(target="id", source="dto.diagnosisId")
    @Mapping(target="description", source="dto.diagnosisDescription")
    Diagnosis diagnosisDTOtoDiagnosis(DiagnosisDto dto);

}
