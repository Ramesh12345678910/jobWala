package com.project.profile_Service.controller;

import com.project.profile_Service.dto.ProfileRequestDto;
import com.project.profile_Service.dto.ProfileResponseDto;
import com.project.profile_Service.service.ProfileService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    private ProfileService profileService;
//    ProfileResponseDto createProfile(Integer userId, ProfileRequestDto profileRequestDto);
//    ProfileResponseDto updateProfile(Integer userId, ProfileRequestDto profileRequestDto);
//    ProfileResponseDto viewProfile(Integer userId);
//    List<ProfileResponseDto> viewAllProfiles();

    @PostMapping("/create/user/{userId}")
    public ResponseEntity<ProfileResponseDto> createProfile(@PathVariable @Min
            (value = 1,message = "UserId must not be 0 or less")Integer userId, @RequestBody @Valid ProfileRequestDto
                                        profileRequestDto){
        return new  ResponseEntity<>(profileService.createProfile(userId,profileRequestDto),HttpStatus.CREATED);
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<ProfileResponseDto> updateProfile(@PathVariable @Min(value=1,
    message="userId must not be 0 or less") Integer userId,@RequestBody @Valid ProfileRequestDto profileRequestDto){
        return new ResponseEntity<>(profileService.updateProfile(userId,profileRequestDto),HttpStatus.ACCEPTED);
    }

    @GetMapping("{userId}")
    public ResponseEntity<ProfileResponseDto> viewProfile(@PathVariable @Min(value = 1,
    message="userId must not be 0 or less )") Integer userId){
        return new ResponseEntity<>(profileService.viewProfile(userId),HttpStatus.OK);

    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ProfileResponseDto>> viewAllProfiles(){
        return new ResponseEntity<>(profileService.viewAllProfiles(),HttpStatus.OK);
    }
}
