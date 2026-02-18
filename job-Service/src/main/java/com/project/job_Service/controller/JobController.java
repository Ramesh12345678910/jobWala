package com.project.job_Service.controller;

import com.project.job_Service.dto.JobRequestDto;
import com.project.job_Service.dto.JobResponseDto;
import com.project.job_Service.security.AuthenticatedUser;
import com.project.job_Service.service.JobService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/job")
public class JobController {
    @Autowired
    private JobService jobservice;

    @PostMapping("/create")
    public ResponseEntity<JobResponseDto> createJob(
            @AuthenticationPrincipal AuthenticatedUser currentUser,
            @RequestBody @Valid JobRequestDto requestDto
    ) {
        return new ResponseEntity<>(jobservice.createJob(currentUser.getUserId(), requestDto), HttpStatus.CREATED);
    }

    @PutMapping("/{jobId}")
    public ResponseEntity<JobResponseDto> updateJob(
            @AuthenticationPrincipal AuthenticatedUser currentUser,
            @PathVariable @Min(value = 1, message = "jobId must not be 0 or less") Integer jobId,
            @RequestBody @Valid JobRequestDto requestDto
    ) {
        return new ResponseEntity<>(jobservice.updateJob(currentUser.getUserId(), jobId, requestDto), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{jobId}")
    public ResponseEntity<?> deleteJob(
            @AuthenticationPrincipal AuthenticatedUser currentUser,
            @PathVariable @Min(value = 1, message = "jobId must not be 0 or less") Integer jobId
    ) {
        jobservice.deleteJob(currentUser.getUserId(), jobId);
        return new ResponseEntity<>("Job post is Deleted", HttpStatus.ACCEPTED);
    }

    @GetMapping("/posted")
    public ResponseEntity<List<JobResponseDto>> postedJobs(@AuthenticationPrincipal AuthenticatedUser currentUser) {
        return new ResponseEntity<>(jobservice.viewJob(currentUser.getUserId()), HttpStatus.OK);
    }

    @GetMapping("/allJobs")
    public ResponseEntity<List<JobResponseDto>> viewAllJobs() {
        return new ResponseEntity<>(jobservice.viewAllJobs(), HttpStatus.OK);
    }

    @GetMapping("/searchJob/{title}")
    public ResponseEntity<List<JobResponseDto>> searchForJob(@PathVariable String title) {
        return new ResponseEntity<>(jobservice.searchForJob(title), HttpStatus.OK);
    }

    @GetMapping("/{jobId}")
    public ResponseEntity<JobResponseDto> getJobById(@PathVariable Integer jobId) {
        return new ResponseEntity<>(jobservice.getJobById(jobId), HttpStatus.OK);
    }
}
