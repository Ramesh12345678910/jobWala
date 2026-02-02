package com.project.application_Service.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobDto {
    @NotNull(message="jobId should not be empty")
    @Min(value=1,message="job Id must not be 0 or less")
    private Integer jobId;
    @NotNull(message="candidateId should not be empty")
    @Min(value=1,message="candidateId must not be 0 or less")
    private Integer employerId;
    @FutureOrPresent(message="No past date is allowed")
    private LocalDate lastDateToApply;
}
