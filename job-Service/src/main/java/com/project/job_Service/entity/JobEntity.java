package com.project.job_Service.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="jobTable")
public class JobEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer jobId;
    @Column(nullable = false)
    private String title;
    @Column(nullable=false)
    private String description;
    @Column
    private LocalDate lastDateToApply;
    @Column(nullable = false)
    private Integer employerId;

    public JobEntity() {
    }

    public JobEntity(String title, String description, LocalDate lastDateToApply, Integer employerId) {
        this.title = title;
        this.description = description;
        this.lastDateToApply = lastDateToApply;
        this.employerId = employerId;
    }

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getLastDateToApply() {
        return lastDateToApply;
    }

    public void setLastDateToApply(LocalDate lastDateToApply) {
        this.lastDateToApply = lastDateToApply;
    }

    public Integer getEmployer_id() {
        return employerId;
    }

    public void setEmployer_id(Integer employerId) {
        this.employerId = employerId;
    }
}
