package com.project.socialmediaplatform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.socialmediaplatform.service.FriendListService;

@RestController
@RequestMapping("/")
public class FriendController {
    
    @Autowired
    private FriendListService friendListService;

    
}
