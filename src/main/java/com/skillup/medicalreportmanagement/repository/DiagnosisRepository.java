package com.skillup.medicalreportmanagement.repository;

import com.skillup.medicalreportmanagement.entity.Diagnosis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiagnosisRepository extends JpaRepository<Diagnosis, Integer> {
    Diagnosis findByDoctorAppointmentId(Integer doctorAppointmentId);
}
