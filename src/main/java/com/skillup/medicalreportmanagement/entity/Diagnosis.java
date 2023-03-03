package com.skillup.medicalreportmanagement.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.ToString;

@Entity
@Builder
@ToString
@Table(name="diagnosis")
public class Diagnosis {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private Integer doctorAppointmentId;
    private String description;

}
