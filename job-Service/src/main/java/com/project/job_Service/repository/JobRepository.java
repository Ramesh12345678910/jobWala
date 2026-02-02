package com.project.job_Service.repository;

import com.project.job_Service.entity.JobEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
public interface JobRepository extends JpaRepository<JobEntity, Integer> {

    List<JobEntity> findByEmployerId(Integer employerId);

    List<JobEntity> findByTitleIgnoreCase(String title);
}

