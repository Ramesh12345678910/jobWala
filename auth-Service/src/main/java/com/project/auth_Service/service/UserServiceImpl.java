package com.project.auth_Service.service;

import com.project.auth_Service.Repository.UserRepository;
import com.project.auth_Service.client.NotificationFeignClient;
import com.project.auth_Service.dto.NotificationDto;
import com.project.auth_Service.dto.UserRequestDto;
import com.project.auth_Service.dto.UserResponseDto;
import com.project.auth_Service.exception.AccountBlockedException;
import com.project.auth_Service.exception.AlreadyRegisteredException;
import com.project.auth_Service.exception.UserNotFoundException;
import com.project.auth_Service.model.AccountStatus;
import com.project.auth_Service.model.Role;
import com.project.auth_Service.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private NotificationFeignClient feignClient;

    @Autowired
    private JWTUtil jwtUtil;

    // ================= REGISTER =================
    @Override
    public UserResponseDto registerUser(UserRequestDto requestDto) {

        UserEntity fetched = userRepository.findByName(requestDto.getName());
        if (fetched != null) {
            throw new AlreadyRegisteredException(
                    "Duplicate registration found with this username"
            );
        }

        UserEntity user = new UserEntity();
        user.setName(requestDto.getName());
        user.setEmail(requestDto.getEmail());
        user.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        user.setRole(Role.USER);
        user.setAccountStatus(AccountStatus.ACTIVE);

        UserEntity savedUser = userRepository.save(user);
        NotificationDto email=new NotificationDto();
        email.setTo(user.getEmail());
        email.setSubject("Welcome To JobWala");
        email.setBody("Your registration completed Successfully." +
                "Wishing You a Great Journey Ahead!.");
        feignClient.sendEmail(email);
        return mapToDto(savedUser);
    }

    // ================= LOGIN / VERIFY =================
    @Override
    public String verify(String username, String password) {

        // 1️⃣ Fetch user from DB
        UserEntity dbUser = userRepository.findByName(username);
        if (dbUser == null) {
            throw new UserNotFoundException("User not found, please register first");
        }

        // 2️⃣ BLOCKED CHECK (BEFORE authentication)
        if (dbUser.getAccountStatus() == AccountStatus.BLOCKED) {
            throw new AccountBlockedException("Sorry, your account is blocked");
        }

        // 3️⃣ Authenticate credentials
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );

        // 4️⃣ Generate JWT using DB user
        return jwtUtil.generateToken(dbUser);
    }

    @Override
    public boolean existsById(Integer userId) {
        Optional<UserEntity> user=userRepository.findById(userId);
        return user.isPresent();
    }

    @Override
    public Boolean isEmployer(Integer employerId) {
        UserEntity user=userRepository.findById(employerId).orElseThrow(()->
                new UserNotFoundException("No employer Exists with this Id"));
        if(user.getRole().equals(Role.EMPLOYER)){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public UserResponseDto getUserById(Integer userId) {
        UserEntity user=userRepository.findById(userId).orElseThrow(()->
                new UserNotFoundException("No user Found with this Id"));
        return mapToDto(user);
    }

    @Override
    public void changePrivilageToEmployer(Integer userId) {
        UserEntity user=userRepository.findById(userId).orElseThrow(()->
                new UserNotFoundException("No user Found with this Id"));
        NotificationDto notification=new NotificationDto();
        notification.setTo("palisettibhaskarrameshsai@gmail.com");
        notification.setSubject("Role Changing Privilage");
        notification.setBody("Iam the user please I working in a company please update my " +
                "privilage to an employer."+"\n"+
                "user Name"+user.getName()+"\n"+"userId"+user.getUserId());
        feignClient.sendEmail(notification);

    }

    // ================= BLOCK ACCOUNT =================
    @Override
    @Transactional
    public UserResponseDto blockAccount(Integer userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new UserNotFoundException("User Not Found with this userId"));
        user.setAccountStatus(AccountStatus.BLOCKED);
        return mapToDto(user);
    }

    // ================= ACTIVATE ACCOUNT =================
    @Override
    @Transactional
    public UserResponseDto activateAccount(Integer userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new UserNotFoundException("User Not Found with this userId"));
        user.setAccountStatus(AccountStatus.ACTIVE);
        NotificationDto notification=new NotificationDto();
        notification.setSubject("Account Activated");
        notification.setTo(user.getEmail());
        notification.setBody("Your account has been Activated.");
        feignClient.sendEmail(notification);
        return mapToDto(user);
    }

    // ================= VIEW USERS =================
    @Override
    public List<UserResponseDto> viewAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    // ================= DTO MAPPER =================
    private UserResponseDto mapToDto(UserEntity user) {
        UserResponseDto dto = new UserResponseDto();
        dto.setUserId(user.getUserId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole());
        dto.setAccountStatus(user.getAccountStatus());
        return dto;
    }
}
