package com.project.auth_Service.service;

import com.project.auth_Service.dto.UserRequestDto;
import com.project.auth_Service.dto.UserResponseDto;
import com.project.auth_Service.model.UserEntity;
import jakarta.validation.constraints.Min;

import java.util.List;

public interface UserService {
    UserResponseDto registerUser(UserRequestDto requestDto);
    UserResponseDto blockAccount(Integer userId);
    UserResponseDto activateAccount(Integer userId);
    List<UserResponseDto> viewAllUsers();
    String verify(String user,String password);
    boolean existsById(Integer userId);
    Boolean isEmployer(Integer employerId);
    UserResponseDto getUserById(@Min(value=1,message="user Id must not  be 0 or less") Integer userId);
}
