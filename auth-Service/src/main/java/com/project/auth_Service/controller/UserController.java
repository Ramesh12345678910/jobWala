package com.project.auth_Service.controller;

import com.project.auth_Service.dto.UserRequestDto;

import com.project.auth_Service.model.UserEntity;
import com.project.auth_Service.service.UserService;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;


    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserRequestDto requestDto){
        return new ResponseEntity<>(userService.registerUser(requestDto),HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserRequestDto dto) {
        return ResponseEntity.ok(
                userService.verify(dto.getName(), dto.getPassword())
        );
    }

    @PatchMapping(  "block/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> blockUser(@PathVariable Integer userId){
        return new ResponseEntity<>(userService.blockAccount(userId),HttpStatus.ACCEPTED);
    }
    @PatchMapping("activate/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> activateUser(@PathVariable Integer userId){
        return new ResponseEntity<>(userService.activateAccount(userId),HttpStatus.ACCEPTED);
    }
    @GetMapping("/allUsers")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> viewAllUsers(){
        return new ResponseEntity<>(userService.viewAllUsers(),HttpStatus.OK);
    }

    @GetMapping("/{userId}/exists")
    public ResponseEntity<Boolean> isUserExists(@PathVariable Integer userId) {
        boolean exists = userService.existsById(userId); return ResponseEntity.ok(exists);
    }


    @GetMapping("/validate-employer/{employerId}")
    public ResponseEntity<Boolean> isEmployer(@PathVariable Integer employerId){
        return new ResponseEntity<>(userService.isEmployer(employerId),HttpStatus.OK);
    }

    @GetMapping("/userId/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable @Min(value=1,message="user Id must not  be 0 or less") Integer userId){
        return new ResponseEntity<>(userService.getUserById(userId),HttpStatus.OK);
    }

}
