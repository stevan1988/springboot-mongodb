package com.todcode.restmongodb.api.controller;

import com.todcode.restmongodb.api.dto.request.UserLoginRequest;
import com.todcode.restmongodb.api.dto.request.UserRegistrationRequest;
import com.todcode.restmongodb.api.service.AuthenticationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    AuthenticationService authenticationService;

    @GetMapping("/auth")
    public UserDetails getLoggedUser() {
        return authenticationService.getLogedInUser();
    }

    @PostMapping("/auth/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody UserLoginRequest userLoginRequest) {
        return authenticationService.login(userLoginRequest);
    }

    @PostMapping("/auth/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserRegistrationRequest userRegistrationRequest) {
        return authenticationService.signUp(userRegistrationRequest);
    }
}
