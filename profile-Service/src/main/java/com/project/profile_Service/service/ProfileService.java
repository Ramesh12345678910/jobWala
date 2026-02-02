package com.project.profile_Service.service;

import com.project.profile_Service.dto.ProfileRequestDto;
import com.project.profile_Service.dto.ProfileResponseDto;
import com.project.profile_Service.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ProfileService {
    ProfileResponseDto createProfile(Integer userId,ProfileRequestDto profileRequestDto);
    ProfileResponseDto updateProfile(Integer userId, ProfileRequestDto profileRequestDto);
    ProfileResponseDto viewProfile(Integer userId);
    List<ProfileResponseDto> viewAllProfiles();

}
