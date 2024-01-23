package com.project.socialmediaplatform.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.socialmediaplatform.Exception.ValidationException;
import com.project.socialmediaplatform.model.Comment;
import com.project.socialmediaplatform.model.Post;
import com.project.socialmediaplatform.model.SearchModel;
import com.project.socialmediaplatform.model.User;
import com.project.socialmediaplatform.security.services.UserDetailsImpl;
import com.project.socialmediaplatform.service.CommentService;
import com.project.socialmediaplatform.service.PostService;
import com.project.socialmediaplatform.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@RestController

@RequestMapping("/api/user")
public class UserController extends UserManager{

    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;

    // @PostMapping("/register")
    // public ResponseEntity<User> registerUser(@RequestBody User user) {

    //     if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
    //         throw new ValidationException("Email is required");
    //     }
    //     if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
    //         throw new ValidationException("Password is required");
    //     }

    //     if (user.getUserName() == null) {
    //         throw new ValidationException("Username is required");
    //     }

    //     User registeredUser = userService.registerUser(user);
    //     return ResponseEntity.ok(registeredUser);
    // }

    // @PostMapping("/login")
    // public ResponseEntity<String> authenticateUser(@RequestBody User loginUser) {

    //     if (loginUser.getEmail() == null || loginUser.getEmail().trim().isEmpty()) {
    //         throw new ValidationException("Email is required");
    //     }
    //     if (loginUser.getPassword() == null || loginUser.getPassword().trim().isEmpty()) {
    //         throw new ValidationException("Password is required");
    //     }

    //     User authenticatedUser = userService.authenticateUser(loginUser.getEmail(), loginUser.getPassword());
        
    //     return ResponseEntity.ok("User authenticated successfully");
    // }

    @PutMapping("")
    public ResponseEntity<User> updateUser(@RequestBody User updatedUser) {
        User user = getUserFromAuthentication();
        User updatedUserInfo = userService.updateUser(user.getUserId(), updatedUser);
        return ResponseEntity.ok(updatedUserInfo);
    }

    @DeleteMapping("")
    public ResponseEntity<String> deactivateUser() {
        User user = getUserFromAuthentication();
        userService.deactivateUser(user.getUserId());
        return ResponseEntity.ok("Deactivated Successfully");
    }

    // search a user
    @GetMapping("/search")
    public ResponseEntity<List<User>> searchUser(@ModelAttribute("search") SearchModel searchModel) {
        User u = getUserFromAuthentication();

        List<User> user = new ArrayList<User>();
        if (searchModel.getSearchWord()!=null && !searchModel.getSearchWord().trim().isEmpty()) {
            user = userService.searchUserWithFilter(searchModel);
            return ResponseEntity.ok(user);
        }
        if (searchModel.getEmail()!=null && !searchModel.getEmail().trim().isEmpty()) {
            user.add(0, userService.getUserByEmail(searchModel.getEmail()));
            return ResponseEntity.ok(user);
        }
        return null;
    }

    // addcomment
    @PostMapping("/post/{postId}/comment")
    public ResponseEntity<Comment> addComment(@PathVariable Long postId,@RequestBody Comment commentText) {
        User user = getUserFromAuthentication();
        if (commentText.getCommentText() == null || commentText.getCommentText().trim().isEmpty()) {
            throw new ValidationException("Comment field is empty");
        }
        Comment addedComment = commentService.addComment(postId, commentText.getCommentText(), user.getUserId());
        return ResponseEntity.ok(addedComment);
    }

}
