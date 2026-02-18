package com.project.application_Service.controller;
import com.project.application_Service.dto.ApplicationRequestDto;
import com.project.application_Service.dto.ApplicationResponseDto;
import com.project.application_Service.security.AuthenticatedUser;
import com.project.application_Service.service.ApplicationService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/application")
public class ApplicationController {
    @Autowired
    private ApplicationService applicationService;


    @PostMapping("/job/{jobId}")
    public ResponseEntity<ApplicationResponseDto> applyForJob(
            @AuthenticationPrincipal AuthenticatedUser currentUser,
            @PathVariable @Min(value = 1, message = "jobId must not be 0 or less") Integer jobId
    ) {
        return new ResponseEntity<>(applicationService.applyForJob(currentUser.getUserId(), jobId), HttpStatus.CREATED);
    }

    @GetMapping("/job/{jobId}")
    public ResponseEntity<List<ApplicationResponseDto>> viewApplicationsByJob(
            @AuthenticationPrincipal AuthenticatedUser currentUser,
            @PathVariable @Min(value = 1, message = "jobId must not be 0 or less") Integer jobId
    ) {
        return new ResponseEntity<>(applicationService.viewApplicationsByJob(currentUser.getUserId(), jobId), HttpStatus.OK);
    }

    @PatchMapping("/status/{applicationId}")
    public ResponseEntity<ApplicationResponseDto> updateApplicationStatus(
            @AuthenticationPrincipal AuthenticatedUser currentUser,
            @PathVariable @Min(value = 1, message = "applicationId must not be 0 or less") Integer applicationId,
            @RequestBody ApplicationRequestDto applicationRequestDto
    ) {
        return new ResponseEntity<>(applicationService.updateApplicationStatus(currentUser.getUserId(), applicationId, applicationRequestDto), HttpStatus.ACCEPTED);
    }

    @GetMapping("/me")
    public ResponseEntity<List<ApplicationResponseDto>> appliedJobs(@AuthenticationPrincipal AuthenticatedUser currentUser) {
        return new ResponseEntity<>(applicationService.viewAllAppliedJobs(currentUser.getUserId()), HttpStatus.OK);
    }
}
