package com.project.socialmediaplatform.controller;

import org.springframework.web.bind.annotation.RestController;

import com.project.socialmediaplatform.model.Like;
import com.project.socialmediaplatform.model.LikeKey;
import com.project.socialmediaplatform.service.LikesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/api/like/")
public class LikeController {
     @Autowired
    private LikesService likeService;

    @PostMapping("{userId}/post/{postId}")
    public ResponseEntity<Like> addLikeForPost( @PathVariable Long postId, @PathVariable Long userId) {
        Like addedLike = likeService.addLikeForPost(userId,postId);
        return ResponseEntity.ok(addedLike);
    }
    @PostMapping("{userId}/comment/{commentId}")
    public ResponseEntity<Like> addLikeForComment( @PathVariable Long commentId, @PathVariable Long userId) {
        Like addedLike = likeService.addLikeForComment(userId,commentId);
        return ResponseEntity.ok(addedLike);
    }

    @DeleteMapping("/post/{postId}")
    public ResponseEntity<String> removeLikeForPost(@RequestBody LikeKey likekey) {
        likeService.removeLikeForPost(likekey);
        return ResponseEntity.ok("Like for post removed successfully");
    
    }
    @DeleteMapping("/comment")
    public ResponseEntity<String> removeLikeForComment(@RequestBody LikeKey likekey) {
        likeService.removeLikeForPost(likekey);
        return ResponseEntity.ok("Like for comment removed successfully");
    }

}
