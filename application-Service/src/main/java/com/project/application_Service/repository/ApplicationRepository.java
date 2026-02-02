package com.project.application_Service.repository;

import com.project.application_Service.model.ApplicationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<ApplicationEntity,Integer> {
    List<ApplicationEntity> findByJobId(Integer jobId);
}
