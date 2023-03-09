package com.skillup.medicalreportmanagement.service;

import com.skillup.medicalreportmanagement.dto.DiagnosisDto;
import com.skillup.medicalreportmanagement.entity.Diagnosis;
import com.skillup.medicalreportmanagement.exception.DiagnosisExistException;
import com.skillup.medicalreportmanagement.exception.DiagnosisNotFoundException;
import com.skillup.medicalreportmanagement.mapper.DiagnosisMapper;
import com.skillup.medicalreportmanagement.repository.DiagnosisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DiagnosisService {

    @Autowired
    private DiagnosisRepository diagnosisRepository;

    @Autowired
    private DiagnosisMapper diagnosisMapper;

    public DiagnosisDto createDiagnosis(DiagnosisDto diagnosisDto) {
        Diagnosis diagnosisExist = diagnosisRepository.findByDoctorAppointmentId(diagnosisDto.getDoctorAppointmentId());
        if(diagnosisExist != null) {
            throw new DiagnosisExistException("Diagnosis record already exist for the given appointment id: " + diagnosisDto.getDoctorAppointmentId());
        }

        Diagnosis diagnosis = diagnosisMapper.diagnosisDTOtoDiagnosis(diagnosisDto);

        return  diagnosisMapper.diagnosisToDiagnosisDTO(diagnosisRepository.save(diagnosis));
    }

    public DiagnosisDto updateDiagnosis(DiagnosisDto diagnosisDto) {
        Optional<Diagnosis> diagnosisExist = diagnosisRepository.findById(diagnosisDto.getDiagnosisId());
        if(diagnosisExist.isEmpty()) {
            throw new DiagnosisNotFoundException("Diagnosis not found for the given diagnosis id: " + diagnosisDto.getDiagnosisId());
        }

        Diagnosis diagnosis = diagnosisMapper.diagnosisDTOtoDiagnosis(diagnosisDto);

        return  diagnosisMapper.diagnosisToDiagnosisDTO(diagnosisRepository.save(diagnosis));
    }

    public DiagnosisDto getDiagnosisByAppointmentId(Integer doctorAppointmentId) {
        Diagnosis diagnosisExist = diagnosisRepository.findByDoctorAppointmentId(doctorAppointmentId);
        if(diagnosisExist == null) {
            throw new DiagnosisNotFoundException("Diagnosis not found for the given appointment id: " + doctorAppointmentId);
        }

        return diagnosisMapper.diagnosisToDiagnosisDTO(diagnosisExist);
    }
}
