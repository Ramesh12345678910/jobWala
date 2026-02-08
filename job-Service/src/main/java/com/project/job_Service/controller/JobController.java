package com.project.job_Service.controller;

import com.project.job_Service.dto.JobRequestDto;
import com.project.job_Service.dto.JobResponseDto;
import com.project.job_Service.service.JobService;
import jakarta.validation.Path;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/job")
public class JobController {
    @Autowired
    private JobService jobservice;

    @PostMapping("/create/employer/{employerId}")
    public ResponseEntity<JobResponseDto> createJob(@PathVariable @Min(value=1,
            message="employerId must not be 0 or less") Integer employerId
            , @RequestBody @Valid JobRequestDto requestDto){
        return new ResponseEntity<>(jobservice.createJob(employerId,requestDto), HttpStatus.CREATED);
    }

    @PutMapping("/update/employer/{employerId}/{jobId}")
    public ResponseEntity<JobResponseDto> updateJob(@PathVariable @Min(value=1,message="employerId must not be 0 or less") Integer employerId,
                                                    @PathVariable @Min(value=1,message="jobId must not be 0 or less") Integer jobId
            , @RequestBody @Valid JobRequestDto requestDto){
        return new ResponseEntity<>(jobservice.updateJob(employerId, jobId, requestDto),HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/employer/{employerId}/job/{jobId}")
    public ResponseEntity<?> deleteJob(@PathVariable @Min(value=1,message="employerId must not be 0 or less") Integer employerId,
                                                    @PathVariable @Min(value=1,message="jobId must not be 0 or less") Integer jobId){
        jobservice.deleteJob(employerId, jobId);
        return new ResponseEntity<>("Job post is Deleted",HttpStatus.ACCEPTED);
    }
    @GetMapping("/PostedJobs/{employerId}")
    public ResponseEntity<List<JobResponseDto>> PostedJobs(@PathVariable @Min(value=1,message="employerId must not be 0 or less")Integer employerId){
        return new ResponseEntity<>(jobservice.viewJob(employerId),HttpStatus.OK);
    }
    @GetMapping("/allJobs")
    public ResponseEntity<List<JobResponseDto>> viewAllJobs(){
        return new ResponseEntity<>(jobservice.viewAllJobs(),HttpStatus.OK);
    }
    @GetMapping("/searchJob/{title}")
    public ResponseEntity<List<JobResponseDto>> searchForJob(@PathVariable String title){
        return new ResponseEntity<>(jobservice.searchForJob(title),HttpStatus.OK);
    }

    @GetMapping("/{jobId}")
    public ResponseEntity<JobResponseDto> getJobById(@PathVariable Integer jobId){
        return new ResponseEntity<>(jobservice.getJobById(jobId),HttpStatus.OK);
    }
}
