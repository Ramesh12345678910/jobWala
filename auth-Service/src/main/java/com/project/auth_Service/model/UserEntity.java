package com.project.auth_Service.model;

import jakarta.persistence.*;

@Entity
@Table(name="usertable")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    @Column(unique = true)
    private String name;
    @Column(unique = true)
    private String email;
    @Column
    private String password;
    @Column
    @Enumerated(EnumType.STRING)
    private Role role;
    @Column
    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;


    public UserEntity(String name, String email, String password, Role role, AccountStatus accountStatus) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.accountStatus=accountStatus;
    }

    public UserEntity() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }
}
