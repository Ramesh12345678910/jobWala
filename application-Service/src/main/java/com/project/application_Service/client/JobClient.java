package com.project.application_Service.client;

import com.project.application_Service.dto.JobDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "job-Service")
public interface JobClient {

    @GetMapping("/job/{jobId}")
    JobDto getJobById(@PathVariable Integer jobId);
}

