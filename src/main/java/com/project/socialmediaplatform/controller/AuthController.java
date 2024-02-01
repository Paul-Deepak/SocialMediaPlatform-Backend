package com.project.socialmediaplatform.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.socialmediaplatform.Exception.AuthenticationException;
import com.project.socialmediaplatform.Exception.ValidationException;
import com.project.socialmediaplatform.model.User;
import com.project.socialmediaplatform.payload.Request.LoginRequest;
import com.project.socialmediaplatform.payload.Response.MessageResponse;
import com.project.socialmediaplatform.payload.Response.UserInfoResponse;
import com.project.socialmediaplatform.repository.UserRepo;
import com.project.socialmediaplatform.security.jwt.JwtUtils;
import com.project.socialmediaplatform.security.services.UserDetailsImpl;
import com.project.socialmediaplatform.service.UserService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/auth")
// @CrossOrigin("*")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepo userRepo;

    @Autowired
    UserService userService;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        System.out.println("Request Body: " + loginRequest.getEmail());
        System.out.println("Request Body: " + loginRequest.toString());
        User user = userRepo.findByEmail(loginRequest.getEmail());
        
        if (user == null)
            throw new AuthenticationException("Invalid Email");

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),
                        loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        String jwtCookie = jwtUtils.generateTokenFromEmail(userDetails.getEmail());

        return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, jwtCookie)
        .header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.AUTHORIZATION)
        .header(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, "Authorization, Content-Type")
                .body(new UserInfoResponse(userDetails.getId(),
                        userDetails.getUsername(),
                        userDetails.getEmail()));
       
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {

        if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
            throw new ValidationException("Email is required");
        }
        if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            throw new ValidationException("Password is required");
        }

        if (user.getUserName() == null) {
            throw new ValidationException("Username is required");
        }

        userService.registerUser(user);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

}
