package com.project.auth_Service.service;

import com.project.auth_Service.Repository.UserRepository;
import com.project.auth_Service.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user=userRepository.findByName(username);
        if(user==null){
            throw new UsernameNotFoundException("User is not founded");
        }
        return new CustomDetails(user);
    }
}
