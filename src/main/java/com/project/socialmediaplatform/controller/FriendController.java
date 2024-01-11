package com.project.socialmediaplatform.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import com.project.socialmediaplatform.service.FriendListService;

@RestController
@RequestMapping("/api/user/")
public class FriendController {

    @Autowired
    private FriendListService friendListService;

    @PostMapping("/{userId}/friend/{friendId}")
    public ResponseEntity<Friend> sendFriendRequest(@PathVariable User userId, @PathVariable User friendId) {
        Friend friendReq = friendListService.sendFriendRequest(userId, friendId);
        return ResponseEntity.ok(friendReq);
    }

    @PutMapping("/{userId}/friend/{friendId}")
    public ResponseEntity<Friend> acceptFriendRequest(@PathVariable Long friendId, @PathVariable Long userId) {
        Friend acceptReq = friendListService.acceptFriendRequest(friendId, userId);
        return ResponseEntity.ok(acceptReq);
    }

    @DeleteMapping("/{userId}/friend/{friendId}")
    public ResponseEntity<Friend> rejectFriendRequest(@PathVariable Long friendId, @PathVariable Long userId) {
        Friend rejectReq = friendListService.rejectFriendRequest(friendId, userId);
        return ResponseEntity.ok(rejectReq);
    }

    @GetMapping("/{userId}/friend")
    public ResponseEntity<List<Friend>> seePendingFriendRequests(@PathVariable Long userId) {
        List<Friend> pendingReq = friendListService.seePendingFriendRequests(userId);
        return ResponseEntity.ok(pendingReq);
    }

    @GetMapping("/{userId}/friends")
    public ResponseEntity<List<Friend>> getFriends(@PathVariable Long userId) {
        List<Friend> friends = friendListService.getFriends(userId);
        return ResponseEntity.ok(friends);
    }

    // unfriend a friend

}
