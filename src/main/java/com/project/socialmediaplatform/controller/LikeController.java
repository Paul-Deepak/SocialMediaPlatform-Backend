package com.project.socialmediaplatform.controller;

import org.springframework.web.bind.annotation.RestController;

import com.project.socialmediaplatform.Exception.ValidationException;
import com.project.socialmediaplatform.model.Like;
import com.project.socialmediaplatform.model.User;
import com.project.socialmediaplatform.repository.UserRepo;
import com.project.socialmediaplatform.security.services.UserDetailsImpl;
import com.project.socialmediaplatform.service.LikesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/user/{type}/{typeId}/like")
public class LikeController extends UserManager {
    @Autowired
    private LikesService likeService;
    @Autowired
    private UserRepo userRepo;

    @PostMapping
    public ResponseEntity<Like> addLike(@PathVariable String type, @PathVariable Long typeId) {
        User user = getUserFromAuthentication();
        Long userId = user.getUserId();
        if (userId == null || type == null || typeId == null) {
            throw new ValidationException("Required fields are missing");
        }
        if (type.equals("post")) {
            Like addedLike = likeService.addLikeForPost(userId, type, typeId);
            return ResponseEntity.ok(addedLike);
        } else if (type.equals("comment")) {
            Like addedLike = likeService.addLikeForComment(userId, type, typeId);
            return ResponseEntity.ok(addedLike);
        }
        return null;
    }

    @DeleteMapping
    public ResponseEntity<String> removeLike(@PathVariable String type, @PathVariable Long typeId) {
        User user = getUserFromAuthentication();
        Long userId = user.getUserId();
        if (userId == null || type == null || typeId == null) {
            throw new ValidationException("Required fields are missing");
        }
        if (type.equals("post")) {
            likeService.removeLikeForPost(userId, type, typeId);
            return ResponseEntity.ok("Like for post removed successfully");
        } else if (type.equals("comment")) {
            likeService.removeLikeForComment(userId, type, typeId);
            return ResponseEntity.ok("Like for comment removed successfully");
        }
        return null;
    }

}
