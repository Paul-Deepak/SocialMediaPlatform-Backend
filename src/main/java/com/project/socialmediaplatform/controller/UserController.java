package com.project.socialmediaplatform.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.socialmediaplatform.model.Comment;
import com.project.socialmediaplatform.model.Post;
import com.project.socialmediaplatform.model.User;
import com.project.socialmediaplatform.service.CommentService;
import com.project.socialmediaplatform.service.PostService;
import com.project.socialmediaplatform.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController

@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;
    

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        User registeredUser = userService.registerUser(user);
        return ResponseEntity.ok(registeredUser);
    }

    // @PostMapping("/login")
    // public ResponseEntity<User> userLogin(@RequestBody User user){
    //     User userLogin = userService.userLogin(user);
    //     return ResponseEntity.ok(user);
    // }

    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable Long userId, @RequestBody User updatedUser) {
        User updatedUserInfo = userService.updateUser(userId, updatedUser);
        return ResponseEntity.ok(updatedUserInfo);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deactivateUser(@PathVariable Long userId) {
        userService.deactivateUser(userId);
        return ResponseEntity.ok("Deactivated Successfully");
    }

    //addcomment
    @PostMapping("/{userId}/post/{postId}/comment")
    public ResponseEntity<Comment> addComment(
            @PathVariable Long userId,
            @PathVariable Long postId,
            @RequestBody String commentText) {
        Comment addedComment = commentService.addComment(postId, commentText, userId);
        return ResponseEntity.ok(addedComment);
    }

}
