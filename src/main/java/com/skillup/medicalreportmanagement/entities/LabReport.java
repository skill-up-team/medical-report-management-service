package com.skillup.medicalreportmanagement.entities;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.ToString;

@Entity
@Builder
@ToString
@Table(name="lab_report")
public class LabReport {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private Integer labAppointmentId;
    private String testResults;
}
