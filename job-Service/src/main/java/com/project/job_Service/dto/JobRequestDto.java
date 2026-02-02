package com.project.job_Service.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobRequestDto {
    @NotEmpty(message = "name should not be null or empty")
    @Pattern(regexp = "^[A-Za-z]+( [A-Za-z]+)*$",
            message="name should contain alphabets")
    @Size(min = 2,max=30)
    private String title;
    @NotEmpty
    @Size(min=5)
    private String description;
    @FutureOrPresent
    private LocalDate lastDateToApply;
    @NotNull(message="employee_id must be null")
    @Min(value=1,message="employee_id must not be zero or less")
    private Integer employerId;

}
