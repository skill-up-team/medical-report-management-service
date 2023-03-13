package com.skillup.medicalreportmanagement.repository;

import com.skillup.medicalreportmanagement.entity.LabReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LabReportRepository extends JpaRepository<LabReport, Integer> {
    LabReport findByLabAppointmentId(Integer labAppointmentId);
}
