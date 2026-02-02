package com.project.application_Service.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="applicationTable")
public class ApplicationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer applicationId;
    @Column(nullable=false)
    private Integer jobId;
    @Column(nullable=false)
    private Integer candidateId;
    @Column(nullable=false)
    private LocalDate appliedDate;
    @Column
    @Enumerated(EnumType.STRING)
    private ApplicationStatus applicationStatus;

    public ApplicationEntity(Integer jobId, Integer candidateId, LocalDate appliedDate, ApplicationStatus applicationStatus) {
        this.jobId = jobId;
        this.candidateId = candidateId;
        this.appliedDate = appliedDate;
        this.applicationStatus = applicationStatus;
    }

    public ApplicationEntity() {
    }

    public Integer getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Integer applicationId) {
        this.applicationId = applicationId;
    }

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public Integer getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(Integer candidateId) {
        this.candidateId = candidateId;
    }

    public LocalDate getAppliedDate() {
        return appliedDate;
    }

    public void setAppliedDate(LocalDate appliedDate) {
        this.appliedDate = appliedDate;
    }

    public ApplicationStatus getApplicationStatus() {
        return applicationStatus;
    }

    public void setApplicationStatus(ApplicationStatus applicationStatus) {
        this.applicationStatus = applicationStatus;
    }
}
