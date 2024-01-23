package com.project.socialmediaplatform.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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

@RestController
@RequestMapping("/api/auth")
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
                .body(new UserInfoResponse(userDetails.getId(),
                        userDetails.getUsername(),
                        userDetails.getEmail()));
       
              
        // } else {
        //     throw new AuthenticationException(
        //             "Invalid credentials   " + " ******* " + user.getPassword());
        // }
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

        User newUser = userService.registerUser(user);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    // @PostMapping("/signout")
    // public ResponseEntity<?> logoutUser() {
    //     ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
    //     return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
    //             .body(new MessageResponse("You've been signed out!"));
    // }
}
