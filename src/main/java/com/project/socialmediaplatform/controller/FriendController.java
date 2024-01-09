package com.project.socialmediaplatform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.socialmediaplatform.model.Friend;
import com.project.socialmediaplatform.model.User;
import com.project.socialmediaplatform.service.FriendListService;

@RestController
@RequestMapping("/api/friend")
public class FriendController {
    
    @Autowired
    private FriendListService friendListService;

    @PostMapping("/sendReq/{friendId}")
    public ResponseEntity<Friend> sendFriendRequest(@RequestBody User userId, @PathVariable User friendId){
        Friend friendReq=friendListService.sendFriendRequest(userId, friendId);
        return ResponseEntity.ok(friendReq);

    }

    // @PutMapping("acceptReq/{friendId}")
    // public ResponseEntity<Friend> acceptFriendRequest(@RequestBody User userId, @PathVariable User friendId){
    //     Friend acceptReq = friendListService.acceptFriendRequest(userId, friendId);
    //     return ResponseEntity.ok(acceptReq);
        
    // }
}
