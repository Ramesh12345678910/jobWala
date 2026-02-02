package com.project.profile_Service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileResponseDto {
    private Integer profileId;
    private String education;
    private Integer Experience;
    private List<String> skills;
    private Integer userId;
}
