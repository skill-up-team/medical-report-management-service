package com.skillup.medicalreportmanagement;

import com.skillup.medicalreportmanagement.dto.DiagnosisDto;
import com.skillup.medicalreportmanagement.entity.Diagnosis;
import com.skillup.medicalreportmanagement.mapper.EntityDtoMapper;
import com.skillup.medicalreportmanagement.repository.DiagnosisRepository;
import com.skillup.medicalreportmanagement.service.DiagnosisService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@SpringBootTest
public class DiagnosisServiceTest {
    @Autowired
    private EntityDtoMapper entityDtoMapper;
    @Mock
    private DiagnosisRepository diagnosisRepository;
    @Autowired
    private DiagnosisService diagnosisService;

    @Test
    void contextLoads() {
    }

    @Test
    public void createDiagnosisUnitTest() {

        // Mock request body data
        DiagnosisDto requestDiagnosisDto = DiagnosisDto.builder()
                .diagnosisDescription("Test diagnosis data")
                .doctorAppointmentId(4)
                .build();

        //Mock response return data
        DiagnosisDto expectedDiagnosisDto = DiagnosisDto.builder()
                .diagnosisId(10)
                .diagnosisDescription("Test diagnosis data")
                .doctorAppointmentId(4)
                .build();

        given(diagnosisRepository.findByDoctorAppointmentId(requestDiagnosisDto.getDoctorAppointmentId())).willReturn(null);

        given(diagnosisRepository.save(entityDtoMapper.diagnosisDTOtoDiagnosis(requestDiagnosisDto))).willReturn(entityDtoMapper.diagnosisDTOtoDiagnosis(expectedDiagnosisDto));

        DiagnosisDto savedDiagnosisDto = diagnosisService.createDiagnosis(requestDiagnosisDto);

        assertEquals("Compare objects diagnosis DTOs",expectedDiagnosisDto, savedDiagnosisDto);
    }

    @Test
    public void updateDiagnosisTest() {

        //Exist diagnosis obj
        Diagnosis diagnosis = Diagnosis.builder()
                .id(9)
                .description("Test")
                .doctorAppointmentId(3)
                .build();

        // Mock request body data
        DiagnosisDto requestDiagnosisDto = DiagnosisDto.builder()
                .diagnosisId(9)
                .diagnosisDescription("Test result for diagnosis 9")
                .doctorAppointmentId(3)
                .build();

        //Mock response return data
        DiagnosisDto expectedDiagnosisDto = DiagnosisDto.builder()
                .diagnosisId(9)
                .diagnosisDescription("Test result for diagnosis 9")
                .doctorAppointmentId(3)
                .build();

        given(diagnosisRepository.findById(requestDiagnosisDto.getDiagnosisId())).willReturn(Optional.ofNullable(diagnosis));

        given(diagnosisRepository.save(entityDtoMapper.diagnosisDTOtoDiagnosis(requestDiagnosisDto))).willReturn(entityDtoMapper.diagnosisDTOtoDiagnosis(expectedDiagnosisDto));

        DiagnosisDto savedDiagnosisDto = diagnosisService.updateDiagnosis(requestDiagnosisDto);

        assertEquals("Compare objects diagnosis DTOs",expectedDiagnosisDto, savedDiagnosisDto);

    }

    @Test
    public void getDiagnosisByAppointmentIdTest() {

        Integer doctorAppointmentId = 3;

        //Mock response return data
        DiagnosisDto expectedDiagnosisDto = DiagnosisDto.builder()
                .diagnosisId(9)
                .diagnosisDescription("Test result for diagnosis 9")
                .doctorAppointmentId(3)
                .build();

        given(diagnosisRepository.findByDoctorAppointmentId(doctorAppointmentId)).willReturn(entityDtoMapper.diagnosisDTOtoDiagnosis(expectedDiagnosisDto));

        DiagnosisDto existDiagnosisDto = diagnosisService.getDiagnosisByAppointmentId(doctorAppointmentId);

        assertEquals("Compare objects", expectedDiagnosisDto, existDiagnosisDto);

    }

}
