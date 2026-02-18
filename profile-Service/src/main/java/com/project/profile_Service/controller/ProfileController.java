package com.project.profile_Service.controller;

import com.project.profile_Service.dto.ProfileRequestDto;
import com.project.profile_Service.dto.ProfileResponseDto;
import com.project.profile_Service.security.AuthenticatedUser;
import com.project.profile_Service.service.ProfileService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @PostMapping("/me")
    public ResponseEntity<ProfileResponseDto> createProfile(
            @AuthenticationPrincipal AuthenticatedUser currentUser,
            @RequestBody @Valid ProfileRequestDto profileRequestDto
    ) {
        return new ResponseEntity<>(profileService.createProfile(currentUser.getUserId(), profileRequestDto), HttpStatus.CREATED);
    }

    @PutMapping("/me")
    public ResponseEntity<ProfileResponseDto> updateProfile(
            @AuthenticationPrincipal AuthenticatedUser currentUser,
            @RequestBody @Valid ProfileRequestDto profileRequestDto
    ) {
        return new ResponseEntity<>(profileService.updateProfile(currentUser.getUserId(), profileRequestDto), HttpStatus.ACCEPTED);
    }

    @GetMapping("/me")
    public ResponseEntity<ProfileResponseDto> viewProfile(@AuthenticationPrincipal AuthenticatedUser currentUser) {
        return new ResponseEntity<>(profileService.viewProfile(currentUser.getUserId()), HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ProfileResponseDto>> viewAllProfiles() {
        return new ResponseEntity<>(profileService.viewAllProfiles(), HttpStatus.OK);
    }
}
