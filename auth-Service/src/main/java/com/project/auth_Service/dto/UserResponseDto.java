package com.project.auth_Service.dto;

import com.project.auth_Service.model.AccountStatus;
import com.project.auth_Service.model.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {

    private Integer userId;
    private String name;
    private String email;
    @Enumerated(EnumType.STRING)
    private Role role;
    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;
}
