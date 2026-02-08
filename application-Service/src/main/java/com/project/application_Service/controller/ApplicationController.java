package com.project.application_Service.controller;
import com.project.application_Service.dto.ApplicationRequestDto;
import com.project.application_Service.dto.ApplicationResponseDto;
import com.project.application_Service.service.ApplicationService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/application")
public class ApplicationController {
    @Autowired
    private ApplicationService applicationService;


    @PostMapping("/candidate/{candidateId}/job/{jobId}")
    public ResponseEntity<ApplicationResponseDto> applyForJob(@PathVariable @Min(value=1,
            message="candidateId must not be 0 or less") Integer candidateId
            , @PathVariable @Min(value=1,message="userId must not be 0 or less") Integer jobId
    ){
        return new ResponseEntity<>(applicationService.applyForJob(candidateId, jobId), HttpStatus.CREATED);
    }

    @GetMapping("/employer/{employerId}/job/{jobId}")
    public ResponseEntity<List<ApplicationResponseDto>> viewApplicationsByJob(@PathVariable @Min(value=1
    ,message="employerId must not ne 0 or less") Integer employerId
    ,@PathVariable @Min(value=1,message="jobId must not be 0 or less") Integer jobId){
        return new ResponseEntity<>(applicationService.viewApplicationsByJob(employerId, jobId),HttpStatus.OK);
    }

    @PatchMapping("/employer/{employerId}/application/{applicationId}")
    public ResponseEntity<ApplicationResponseDto> updateApplicationStatus(@PathVariable @Min(value=1,
    message="employerId must not be 0 or less") Integer employerId,@PathVariable @Min(value=1
    ,message = "applicationId must not be 0 or less") Integer applicationId,@RequestBody ApplicationRequestDto applicationRequestDto){
        return new ResponseEntity<>(applicationService.updateApplicationStatus(employerId, applicationId, applicationRequestDto),HttpStatus.ACCEPTED);
    }
    @GetMapping("/candidate/{candidateId}")
    public ResponseEntity<List<ApplicationResponseDto>> appliedJobs(@PathVariable Integer candidateId){
        return new ResponseEntity<>(applicationService.viewAllAppliedJobs(candidateId),HttpStatus.OK);
    }
}
