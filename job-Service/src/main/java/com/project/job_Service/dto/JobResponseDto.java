package com.project.job_Service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobResponseDto {
    private Integer jobId;
    private String title;
    private String description;
    private LocalDate lastDateToApply;
    private Integer employerId;
}