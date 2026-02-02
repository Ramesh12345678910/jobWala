package com.project.profile_Service.service;

import com.project.profile_Service.client.UserClient;
import com.project.profile_Service.dto.ProfileRequestDto;
import com.project.profile_Service.dto.ProfileResponseDto;
import com.project.profile_Service.exception.ProfileAlreadyExistsForUser;
import com.project.profile_Service.exception.ProfileNotFoundException;
import com.project.profile_Service.exception.UserNotFoundException;
import com.project.profile_Service.model.ProfileEntity;
import com.project.profile_Service.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private UserClient userClient;

    @Override
    public ProfileResponseDto createProfile(Integer userId, ProfileRequestDto dto) {

        Boolean exists = userClient.isUserExists(userId);
        if (!exists) {
            throw new UserNotFoundException("User does not exist");
        }

        if (profileRepository.existsByUserId(userId)) {
            throw new ProfileAlreadyExistsForUser("Profile already exists");
        }

        ProfileEntity profile = new ProfileEntity();
        profile.setUserId(userId);
        profile.setEducation(dto.getEducation());
        profile.setExperience(dto.getExperience());
        profile.setSkills(dto.getSkills());

        profileRepository.save(profile);
        return mapToDto(profile);
    }


    @Override
    public ProfileResponseDto updateProfile(Integer userId, ProfileRequestDto dto) {
        ProfileEntity profile = profileRepository.findByUserId(userId);
        if(profile==null){
            throw new ProfileNotFoundException("Profile is not Exists");
        }


        profile.setEducation(dto.getEducation());
        profile.setExperience(dto.getExperience());
        profile.setSkills(dto.getSkills());

        profileRepository.save(profile);
        return mapToDto(profile);
    }

    @Override
    public ProfileResponseDto viewProfile(Integer userId) {
        ProfileEntity profile = profileRepository.findByUserId(userId);
        if(profile==null){
            throw new ProfileNotFoundException("Profile Not Existed");
        }

        return mapToDto(profile);
    }

    @Override
    public List<ProfileResponseDto> viewAllProfiles() {
        List<ProfileEntity> profiles=profileRepository.findAll();
        if(profiles.isEmpty()){
            throw new ProfileNotFoundException("No Profiles are there");
        }
        else{
            return profiles.stream().map(this::mapToDto).toList();
        }
    }

    private ProfileResponseDto mapToDto(ProfileEntity profile) {
        ProfileResponseDto dto = new ProfileResponseDto();
        dto.setProfileId(profile.getProfileId());
        dto.setUserId(profile.getUserId());
        dto.setEducation(profile.getEducation());
        dto.setExperience(profile.getExperience());
        dto.setSkills(profile.getSkills());
        return dto;
    }
}
