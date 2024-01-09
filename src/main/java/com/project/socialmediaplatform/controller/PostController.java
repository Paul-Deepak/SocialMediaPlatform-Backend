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

import com.project.socialmediaplatform.model.Comment;
import com.project.socialmediaplatform.model.Post;
import com.project.socialmediaplatform.model.User;
import com.project.socialmediaplatform.service.PostService;



@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;
    

    // //deletepost
    // @DeleteMapping("post/{postId}")
    // public ResponseEntity<Void> deletePost(@PathVariable Long postId) {
    //     postService.deletePost(postId);
    //     return ResponseEntity.noContent().build();
    // }
    
    // //editpost
    // @PutMapping("/post/{postId}")
    // public ResponseEntity<Post> editPost(@PathVariable Long postId, @RequestBody byte[] updatedContent) {
    //     Post editedPost = postService.editPost(postId, updatedContent);
    //     return ResponseEntity.ok(editedPost);
    // }

}



