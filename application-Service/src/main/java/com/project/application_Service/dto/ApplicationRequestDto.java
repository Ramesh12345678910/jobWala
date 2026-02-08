package com.project.application_Service.dto;

import com.project.application_Service.model.ApplicationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationRequestDto {
    private ApplicationStatus applicationStatus;
}
