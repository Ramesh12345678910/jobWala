package com.project.profile_Service.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileRequestDto {
    @NotEmpty(message="education should not be empty")
    @Pattern(regexp = "[a-zA-z.]+",message="education should contain only dot and alphabets")
    @Size(min=4,max=6)
    private String education;
    @NotNull(message = "experience is required (enter 0 if no experience)")
    @Min(value = 0, message = "experience cannot be negative")
    @Max(value = 40, message = "experience cannot exceed 40 years")
    private Integer experience;
    @NotEmpty(message = "skills list must not be empty")
    @Size(max = 20, message = "maximum 20 skills allowed")
    private List<
            @NotBlank(message = "skill must not be blank")
            @Pattern(
                    regexp = "^[A-Za-z0-9+.# ]+$",
                    message = "skill contains invalid characters"
            )
                    String
            > skills;
//    @NotNull(message = "user should not be null")
//    @Min(value = 1,message = "userId must not be zero or less")
//    private Integer userId;


}