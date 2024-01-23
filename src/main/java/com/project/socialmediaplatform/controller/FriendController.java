package com.project.socialmediaplatform.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.socialmediaplatform.model.Friend;
import com.project.socialmediaplatform.model.User;
import com.project.socialmediaplatform.repository.FriendListRepo;
import com.project.socialmediaplatform.repository.UserRepo;
import com.project.socialmediaplatform.security.services.UserDetailsImpl;
import com.project.socialmediaplatform.service.FriendListService;

@RestController
@RequestMapping("/api/user/")
public class FriendController extends UserManager{

    @Autowired
    private FriendListService friendListService;

    @Autowired
    private FriendListRepo friendListRepo;

    @PostMapping("/friend/{friendId}")
    public ResponseEntity<Friend> sendFriendRequest(@PathVariable User friendId) {
        User userId = getUserFromAuthentication();
        Friend friendReq = friendListService.sendFriendRequest(userId, friendId);
        return ResponseEntity.ok(friendReq);
    }

    @PutMapping("/friend/{friendId}")
    public ResponseEntity<Friend> acceptFriendRequest(@PathVariable Long friendId) {
        User user = getUserFromAuthentication();
        Long userId = user.getUserId();   
        Friend acceptReq = friendListService.acceptFriendRequest(friendId, userId);
        return ResponseEntity.ok(acceptReq);
    }

    @DeleteMapping("/friend/{friendId}")
    public ResponseEntity<Friend> rejectFriendRequest(@PathVariable Long friendId) {
        User user = getUserFromAuthentication();
        Long userId = user.getUserId();
        Friend rejectReq = friendListService.rejectFriendRequest(friendId, userId);
        return ResponseEntity.ok(rejectReq);
    }

    @GetMapping("/friend")
    public ResponseEntity<List<Friend>> seePendingFriendRequests() {
        User user = getUserFromAuthentication();
        Long userId = user.getUserId();
        List<Friend> pendingReq = friendListService.seePendingFriendRequests(userId);
        return ResponseEntity.ok(pendingReq);
    }

    @GetMapping("/friends")
    public ResponseEntity<List<Friend>> getFriends() {
        User user = getUserFromAuthentication();
        Long userId = user.getUserId();
        List<Friend> friends = friendListService.getFriends(userId);
        return ResponseEntity.ok(friends);
    }

    // unfriend a friend

}
