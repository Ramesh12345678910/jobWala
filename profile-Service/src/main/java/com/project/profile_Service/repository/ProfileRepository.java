package com.project.profile_Service.repository;

import com.project.profile_Service.model.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface ProfileRepository extends JpaRepository<ProfileEntity,Integer> {
    ProfileEntity findByUserId(Integer userId);
    boolean existsByUserId(Integer userId);
}
