package com.project.auth_Service.dto;

import com.project.auth_Service.model.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {
    @NotEmpty(message = "name should not be empty")
    @Pattern(regexp = "^[A-Za-z]+( [A-Za-z]+)*$",
            message="name should contain alphabets")
    @Size(min=2,max=30)
    private String name;
    @NotEmpty(message = "email should not be empty")
    @Email(message = "email should be valid")
    private String email;

    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,12}$",
            message="Password must be 8–12 characters long and include at least one uppercase" +
                    " letter, one lowercase letter, one number, and one special character.\n"
    )
    private String password;

}
