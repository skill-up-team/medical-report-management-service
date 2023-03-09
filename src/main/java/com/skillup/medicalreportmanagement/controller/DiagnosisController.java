package com.skillup.medicalreportmanagement.controller;

import com.skillup.medicalreportmanagement.dto.DiagnosisDto;
import com.skillup.medicalreportmanagement.service.DiagnosisService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/${api.current.version}/diagnosis")
public class DiagnosisController {

    @Autowired
    private DiagnosisService diagnosisService;

    @PostMapping
    public ResponseEntity<DiagnosisDto> createDiagnosis(@RequestBody @Valid DiagnosisDto diagnosisDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(diagnosisService.createDiagnosis(diagnosisDto));
    }

    @PutMapping
    public ResponseEntity<DiagnosisDto> updateDiagnosis(@RequestBody @Valid DiagnosisDto diagnosisDto) {
        return ResponseEntity.ok(diagnosisService.updateDiagnosis(diagnosisDto));
    }

    @GetMapping("/{doctorAppointmentId}")
    public ResponseEntity<DiagnosisDto> getDiagnosisData(@PathVariable @Valid Integer doctorAppointmentId) {
        return ResponseEntity.ok(diagnosisService.getDiagnosisByAppointmentId(doctorAppointmentId));
    }
}
