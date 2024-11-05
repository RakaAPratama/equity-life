package com.equality.employee.controller;

import com.equality.employee.request.LoginRequest;
import com.equality.employee.response.auth.LoginResponse;
import com.equality.employee.service.impl.JwtService;
import com.equality.employee.service.impl.MyUserDetailsServiceImpl;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
public class AuthController {

    @Autowired
    JwtService jwtService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    MyUserDetailsServiceImpl myUserDetailsService;

    @PostMapping("/api/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );

        if (authentication.isAuthenticated()) {
            String token
                    = jwtService.generateToken
                    (myUserDetailsService.loadUserByUsername(loginRequest.getUsername()));

            return LoginResponse.builder().token(token).build();
        }
        log.info("Error invalid user");
        throw new UsernameNotFoundException("invalid user");
    }
}
