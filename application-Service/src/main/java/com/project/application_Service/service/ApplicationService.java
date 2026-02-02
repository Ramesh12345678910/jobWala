package com.project.application_Service.service;

import com.project.application_Service.dto.ApplicationRequestDto;
import com.project.application_Service.dto.ApplicationResponseDto;
import com.project.application_Service.repository.ApplicationRepository;

import java.util.List;

public interface ApplicationService {
    ApplicationResponseDto applyForJob(Integer candidateId, Integer jobId, ApplicationRequestDto requestDto);
    List<ApplicationResponseDto> viewApplicationsByJob(Integer employerId, Integer jobId);
    ApplicationResponseDto updateApplicationStatus(Integer employerId, Integer applicationId, ApplicationRequestDto requestDto);
}
