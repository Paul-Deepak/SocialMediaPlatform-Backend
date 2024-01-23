package com.project.socialmediaplatform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.web.exchanges.HttpExchange.Principal;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.project.socialmediaplatform.model.User;
import com.project.socialmediaplatform.repository.UserRepo;
import com.project.socialmediaplatform.security.services.UserDetailsImpl;

public class UserManager {
    @Autowired
    private UserRepo userRepo;
     protected User getUserFromAuthentication() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();   
        String email = ((UserDetailsImpl) principal).getEmail();
        return userRepo.findByEmailId(email).orElse(null);
    }
}
