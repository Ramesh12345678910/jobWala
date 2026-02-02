package com.project.application_Service.dto;

import com.project.application_Service.model.ApplicationStatus;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;import lombok.AllArgsConstructor;import lombok.Data;import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationRequestDto {


    @Enumerated(EnumType.STRING)
    private ApplicationStatus applicationStatus;
}
