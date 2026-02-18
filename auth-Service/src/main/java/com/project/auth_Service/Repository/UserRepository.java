package com.project.auth_Service.Repository;

import com.project.auth_Service.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Integer> {
    UserEntity findByName(String name);
    UserEntity findByEmail(String email);
}
