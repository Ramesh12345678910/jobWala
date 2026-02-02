package com.project.job_Service.service;

import com.project.job_Service.client.AuthFeignClient;
import com.project.job_Service.dto.JobRequestDto;
import com.project.job_Service.dto.JobResponseDto;
import com.project.job_Service.entity.JobEntity;
import com.project.job_Service.exception.JobNotFoundException;
import com.project.job_Service.exception.NotHavePrivilageException;
import com.project.job_Service.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private AuthFeignClient authFeignClient;

    @Override
    public JobResponseDto createJob(Integer employerId, JobRequestDto dto) {

        Boolean isEmployer = authFeignClient.isEmployer(employerId);
        if (!Boolean.TRUE.equals(isEmployer)) {
            throw new NotHavePrivilageException("Only employer can create jobs");
        }

        JobEntity job = new JobEntity();
        job.setEmployer_id(employerId);
        job.setTitle(dto.getTitle());
        job.setDescription(dto.getDescription());
        job.setLastDateToApply(dto.getLastDateToApply());

        return mapToDto(jobRepository.save(job));
    }


    @Override
    public JobResponseDto updateJob(Integer employerId, Integer jobId, JobRequestDto dto) {

        JobEntity job = jobRepository.findById(jobId)
                .orElseThrow(() -> new JobNotFoundException("Job not found"));

        if (!job.getEmployer_id().equals(employerId)) {
            throw new NotHavePrivilageException("You cannot update this job");
        }

        job.setTitle(dto.getTitle());
        job.setDescription(dto.getDescription());
        job.setLastDateToApply(dto.getLastDateToApply());

        return mapToDto(jobRepository.save(job));
    }


    @Override
    public void deleteJob(Integer employerId, Integer jobId) {

        JobEntity job = jobRepository.findById(jobId)
                .orElseThrow(() -> new JobNotFoundException("Job not found"));

        if (!job.getEmployer_id().equals(employerId)) {
            throw new NotHavePrivilageException("You cannot delete this job");
        }

        jobRepository.delete(job);
    }


    @Override
    public List<JobResponseDto> viewJob(Integer employerId) {

        return jobRepository.findByEmployerId(employerId)
                .stream()
                .map(this::mapToDto)
                .toList();   // empty list is OK
    }


    @Override
    public List<JobResponseDto> viewAllJobs() {

        return jobRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .toList();
    }
    @Override
    public List<JobResponseDto> searchForJob(String title) {

        List<JobResponseDto> jobs = jobRepository.findByTitleIgnoreCase(title)
                .stream()
                .map(this::mapToDto)
                .toList();

        if (jobs.isEmpty()) {
            throw new JobNotFoundException("No jobs found for title: " + title);
        }
        return jobs;
    }

    private JobResponseDto mapToDto(JobEntity job) {

        JobResponseDto dto = new JobResponseDto();
        dto.setJobId(job.getJobId());
        dto.setTitle(job.getTitle());
        dto.setDescription(job.getDescription());
        dto.setEmployerId(job.getEmployer_id());
        dto.setLastDateToApply(job.getLastDateToApply());
        return dto;
    }
}
