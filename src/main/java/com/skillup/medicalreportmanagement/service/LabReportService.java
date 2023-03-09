package com.skillup.medicalreportmanagement.service;

import com.skillup.medicalreportmanagement.dto.LabReportDto;
import com.skillup.medicalreportmanagement.entity.LabReport;
import com.skillup.medicalreportmanagement.exception.EntryExistException;
import com.skillup.medicalreportmanagement.exception.EntryNotFoundException;
import com.skillup.medicalreportmanagement.mapper.EntityDtoMapper;
import com.skillup.medicalreportmanagement.repository.LabReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LabReportService {

    @Autowired
    private LabReportRepository labReportRepository;

    @Autowired
    private EntityDtoMapper entityDtoMapper;

    public LabReportDto createLabReport(LabReportDto labReportDto) {
        LabReport labReportExist = labReportRepository.findByLabAppointmentId(labReportDto.getLabAppointmentId());
        if(labReportExist != null) {
            throw new EntryExistException("Lab report already exist for the given lab appointment id: " + labReportDto.getLabAppointmentId());
        }

        LabReport labReport = entityDtoMapper.labReportDTOtoLabReport(labReportDto);

        return  entityDtoMapper.labReportToLabReportDTO(labReportRepository.save(labReport));
    }

    public LabReportDto updateLabReport(LabReportDto labReportDto) {
        Optional<LabReport> labReportExist = labReportRepository.findById(labReportDto.getLabReportId());
        if(labReportExist.isEmpty()) {
            throw new EntryNotFoundException("Lab report not found for the given lab report id: " + labReportDto.getLabReportId());
        }

        LabReport labReport = entityDtoMapper.labReportDTOtoLabReport(labReportDto);

        return  entityDtoMapper.labReportToLabReportDTO(labReportRepository.save(labReport));
    }

    public LabReportDto getDiagnosisByAppointmentId(Integer labAppointmentId) {
        LabReport labReportExist = labReportRepository.findByLabAppointmentId(labAppointmentId);
        if(labReportExist == null) {
            throw new EntryNotFoundException("Lab report not found for the given lab appointment id: " + labAppointmentId);
        }

        return entityDtoMapper.labReportToLabReportDTO(labReportExist);
    }
}
