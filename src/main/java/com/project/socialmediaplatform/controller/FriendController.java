package com.project.socialmediaplatform.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

    @PutMapping("/{userId}/acceptReq/{friendId}")
    public ResponseEntity<Friend> acceptFriendRequest(@PathVariable Long friendId, @PathVariable Long userId){
        Friend acceptReq = friendListService.acceptFriendRequest(friendId, userId);
        return ResponseEntity.ok(acceptReq);    
    }

    @GetMapping("/{userId}/pendingRequests")
    public ResponseEntity<List<Friend>> seePendingFriendRequests(@PathVariable Long userId){
        List<Friend> pendingReq = friendListService.seePendingFriendRequests(userId);
        return ResponseEntity.ok(pendingReq);    
    }

    @GetMapping("/{userId}/friends")
    public ResponseEntity<List<Friend>> getFriends(@PathVariable Long userId){
        List<Friend> friends = friendListService.getFriends(userId);
        return ResponseEntity.ok(friends);
    }

    //rejectreq
    @PutMapping("/{userId}/rejectReq/{friendId}")
    public ResponseEntity<Friend> rejectFriendRequest(@PathVariable Long friendId, @PathVariable Long userId){
        Friend rejectReq = friendListService.rejectFriendRequest(friendId, userId);
        return ResponseEntity.ok(rejectReq);    
    }

    //unfriend a friend

}
