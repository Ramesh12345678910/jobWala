package com.project.application_Service.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    @NotNull(message="userId must not be null")
    @Min(value=1,message="userId not be 0 or less")
    private Integer userId;
    @NotEmpty(message = "role should not be empty")
    private String role;
}