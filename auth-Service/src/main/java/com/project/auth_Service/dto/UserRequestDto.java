package com.project.auth_Service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {
    @NotBlank(message = "name should not be empty")
    @Pattern(
            regexp = "^[A-Za-z]+( [A-Za-z]+)*$",
            message = "name should contain alphabets"
    )
    @Size(min = 2, max = 30, message = "name length should be between 2 and 30")
    private String name;

    @NotBlank(message = "email should not be empty")
    @Email(message = "email should be valid")
    @Pattern(
            regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$",
            message = "email format is invalid"
    )
    private String email;

    @NotBlank(message = "password should not be empty")
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,12}$",
            message = "Password must be 8-12 characters long and include at least one uppercase letter, one lowercase letter, one number, and one special character."
    )
    private String password;
}
