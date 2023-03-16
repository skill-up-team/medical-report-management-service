package com.skillup.medicalreportmanagement;

import com.skillup.medicalreportmanagement.dto.LabReportDto;
import com.skillup.medicalreportmanagement.entity.LabReport;
import com.skillup.medicalreportmanagement.mapper.EntityDtoMapper;
import com.skillup.medicalreportmanagement.repository.LabReportRepository;
import com.skillup.medicalreportmanagement.service.LabReportService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@SpringBootTest
public class LabReportServiceTest {

    @Autowired
    private EntityDtoMapper entityDtoMapper;
    @Mock
    private LabReportRepository labReportRepository;
    @Autowired
    private LabReportService labReportService;

    @Test
    void contextLoads() {
    }

    @Test
    public void createLabReportUnitTest() {

        // Mock request body data
        LabReportDto requestLabReportDto = LabReportDto.builder()
                .labTestResults("Test lab report data")
                .labAppointmentId(2)
                .build();

        //Mock response return data
        LabReportDto expectedLabReportDto = LabReportDto.builder()
                .labReportId(2)
                .labTestResults("Test lab report data")
                .labAppointmentId(2)
                .build();

        given(labReportRepository.findByLabAppointmentId(requestLabReportDto.getLabAppointmentId())).willReturn(null);

        given(labReportRepository.save(entityDtoMapper.labReportDTOtoLabReport(requestLabReportDto))).willReturn(entityDtoMapper.labReportDTOtoLabReport(expectedLabReportDto));

        LabReportDto savedLabReportDto = labReportService.createLabReport(requestLabReportDto);

        assertEquals("Compare objects diagnosis DTOs",expectedLabReportDto, savedLabReportDto);
    }

    @Test
    public void updateLabReportTest() {

        //Exist diagnosis obj
        LabReport diagnosis = LabReport.builder()
                .id(2)
                .testResults("Test lab report data")
                .labAppointmentId(2)
                .build();

        // Mock request body data
        LabReportDto requestLabReportDto = LabReportDto.builder()
                .labReportId(2)
                .labTestResults("Test result for lab report 2")
                .labAppointmentId(2)
                .build();

        //Mock response return data
        LabReportDto expectedLabReportDto = LabReportDto.builder()
                .labReportId(2)
                .labTestResults("Test result for lab report 2")
                .labAppointmentId(2)
                .build();

        given(labReportRepository.findById(requestLabReportDto.getLabReportId())).willReturn(Optional.ofNullable(diagnosis));

        given(labReportRepository.save(entityDtoMapper.labReportDTOtoLabReport(requestLabReportDto))).willReturn(entityDtoMapper.labReportDTOtoLabReport(expectedLabReportDto));

        LabReportDto savedLabReportDto = labReportService.updateLabReport(requestLabReportDto);

        assertEquals("Compare objects diagnosis DTOs",expectedLabReportDto, savedLabReportDto);

    }

    @Test
    public void getLabReportByAppointmentIdTest() {

        Integer labAppointmentId = 2;

        //Mock response return data
        LabReportDto expectedLabReportDto = LabReportDto.builder()
                .labReportId(2)
                .labTestResults("Test result for lab report 2")
                .labAppointmentId(2)
                .build();

        given(labReportRepository.findByLabAppointmentId(labAppointmentId)).willReturn(entityDtoMapper.labReportDTOtoLabReport(expectedLabReportDto));

        LabReportDto existLabReportDto = labReportService.getDiagnosisByAppointmentId(labAppointmentId);

        assertEquals("Compare objects", expectedLabReportDto, existLabReportDto);

    }
}
