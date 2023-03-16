package com.skillup.medicalreportmanagement.service;

import com.skillup.medicalreportmanagement.dto.DiagnosisDto;
import com.skillup.medicalreportmanagement.entity.Diagnosis;
import com.skillup.medicalreportmanagement.exception.EntryExistException;
import com.skillup.medicalreportmanagement.exception.EntryNotFoundException;
import com.skillup.medicalreportmanagement.mapper.EntityDtoMapper;
import com.skillup.medicalreportmanagement.repository.DiagnosisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class DiagnosisService {

    @Autowired
    private DiagnosisRepository diagnosisRepository;

    @Autowired
    private EntityDtoMapper entityDtoMapper;

    public DiagnosisDto createDiagnosis(DiagnosisDto diagnosisDto) {
        Diagnosis diagnosisExist = diagnosisRepository.findByDoctorAppointmentId(diagnosisDto.getDoctorAppointmentId());
        if(diagnosisExist != null) {
            throw new EntryExistException("Diagnosis record already exist for the given appointment id: " + diagnosisDto.getDoctorAppointmentId());
        }

        Diagnosis diagnosis = entityDtoMapper.diagnosisDTOtoDiagnosis(diagnosisDto);

        return  entityDtoMapper.diagnosisToDiagnosisDTO(diagnosisRepository.save(diagnosis));
    }

    public DiagnosisDto updateDiagnosis(DiagnosisDto diagnosisDto) {
        Optional<Diagnosis> diagnosisExist = diagnosisRepository.findById(diagnosisDto.getDiagnosisId());
        if(diagnosisExist.isEmpty()) {
            throw new EntryNotFoundException("Diagnosis not found for the given diagnosis id: " + diagnosisDto.getDiagnosisId());
        }

        if(!Objects.equals(diagnosisExist.get().getDoctorAppointmentId(), diagnosisDto.getDoctorAppointmentId())) {
            Diagnosis diagnosisExistForAppointment = diagnosisRepository.findByDoctorAppointmentId(diagnosisDto.getDoctorAppointmentId());

            if(diagnosisExistForAppointment != null) {
                throw new EntryExistException("Diagnosis record already exist for the given appointment id: " + diagnosisDto.getDoctorAppointmentId());
            }
        }

        Diagnosis diagnosis = entityDtoMapper.diagnosisDTOtoDiagnosis(diagnosisDto);

        return  entityDtoMapper.diagnosisToDiagnosisDTO(diagnosisRepository.save(diagnosis));
    }

    public DiagnosisDto getDiagnosisByAppointmentId(Integer doctorAppointmentId) {
        Diagnosis diagnosisExist = diagnosisRepository.findByDoctorAppointmentId(doctorAppointmentId);
        if(diagnosisExist == null) {
            throw new EntryNotFoundException("Diagnosis not found for the given appointment id: " + doctorAppointmentId);
        }

        return entityDtoMapper.diagnosisToDiagnosisDTO(diagnosisExist);
    }
}
