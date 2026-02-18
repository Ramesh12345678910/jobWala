package com.project.job_Service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(request -> request
                        .requestMatchers(HttpMethod.POST, "/job/create").hasRole("EMPLOYER")
                        .requestMatchers(HttpMethod.PUT, "/job/*").hasRole("EMPLOYER")
                        .requestMatchers(HttpMethod.DELETE, "/job/*").hasRole("EMPLOYER")
                        .requestMatchers(HttpMethod.GET, "/job/posted").hasRole("EMPLOYER")
                        .requestMatchers(HttpMethod.GET, "/job/allJobs").hasAnyRole("USER", "EMPLOYER", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/job/searchJob/**").hasAnyRole("USER", "EMPLOYER", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/job/*").hasAnyRole("USER", "EMPLOYER", "ADMIN")
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
