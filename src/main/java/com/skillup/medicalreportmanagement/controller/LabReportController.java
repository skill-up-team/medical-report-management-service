package com.skillup.medicalreportmanagement.controller;

import com.skillup.medicalreportmanagement.dto.LabReportDto;
import com.skillup.medicalreportmanagement.service.LabReportService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/${api.current.version}/lab-report")
public class LabReportController {

    @Autowired
    private LabReportService labReportService;

    @PostMapping
    public ResponseEntity<LabReportDto> createLabReport(@RequestBody @Validated({LabReportDto.Create.class}) LabReportDto labReportDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(labReportService.createLabReport(labReportDto));
    }

    @PutMapping
    public ResponseEntity<LabReportDto> updateLabReport(@RequestBody @Validated({LabReportDto.Update.class}) LabReportDto labReportDto) {
        return ResponseEntity.ok(labReportService.updateLabReport(labReportDto));
    }

    @GetMapping("/{labAppointmentId}")
    public ResponseEntity<LabReportDto> getLabReportData(@PathVariable @Valid Integer labAppointmentId) {
        return ResponseEntity.ok(labReportService.getDiagnosisByAppointmentId(labAppointmentId));
    }
}
