package com.project.application_Service.dto;

import com.project.application_Service.model.ApplicationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationResponseDto {

    private Integer applicationId;
    private Integer employerId;
    private Integer jobId;
    private Integer candidateId;
    private LocalDate appliedDate;
    private ApplicationStatus status;
}
