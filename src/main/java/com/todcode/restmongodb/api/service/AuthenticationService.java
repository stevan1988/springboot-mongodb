package com.todcode.restmongodb.api.service;

import com.todcode.restmongodb.api.dto.Role;
import com.todcode.restmongodb.api.dto.User;
import com.todcode.restmongodb.api.dto.request.UserLoginRequest;
import com.todcode.restmongodb.api.dto.request.UserRegistrationRequest;
import com.todcode.restmongodb.api.dto.response.ApiResponse;
import com.todcode.restmongodb.api.dto.response.JwtAuthenticationResponse;
import com.todcode.restmongodb.api.dto.type.RoleType;
import com.todcode.restmongodb.config.PlatformJwtConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collections;

@Slf4j
@Service
public class AuthenticationService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    PlatformJwtConfiguration platformJwtConfiguration;

    public UserDetails getLogedInUser(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetails;
    }

    public ResponseEntity<?> login(@Valid @RequestBody UserLoginRequest userLoginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userLoginRequest.getUsername(), userLoginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = platformJwtConfiguration.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    public ResponseEntity<?> signUp(@Valid @RequestBody UserRegistrationRequest UserRegistrationRequest) {
        if (userService.existsByUsername(UserRegistrationRequest.getUsername())) {
            return new ResponseEntity(new ApiResponse(false, "Username is already taken!"), HttpStatus.BAD_REQUEST);
        }
        // creating user's account
        User user = User.builder().firstName(UserRegistrationRequest.getFirstName()).lastName(UserRegistrationRequest.getLastName()).username(UserRegistrationRequest.getUsername())
                .password(passwordEncoder.encode(UserRegistrationRequest.getPassword())).build();
        Role userRole = roleService.findByType(RoleType.ROLE_USER);
        user.setRoles(Collections.singleton(userRole));
        User result = userService.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/users/{username}")
                .buildAndExpand(result.getUsername()).toUri();
        return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
    }
}
