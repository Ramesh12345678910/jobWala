package com.project.job_Service.service;


import com.project.job_Service.dto.JobRequestDto;
import com.project.job_Service.dto.JobResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface JobService {
    JobResponseDto createJob(Integer employerId,JobRequestDto jobRequestDto);
    JobResponseDto updateJob(Integer employerId,Integer jobId,JobRequestDto jobRequestDto);
    void deleteJob(Integer employerId,Integer JobId);
    List<JobResponseDto> viewJob(Integer employerId);
    List<JobResponseDto> viewAllJobs();
    List<JobResponseDto> searchForJob(String title);
    JobResponseDto getJobById(Integer jobId);
}
