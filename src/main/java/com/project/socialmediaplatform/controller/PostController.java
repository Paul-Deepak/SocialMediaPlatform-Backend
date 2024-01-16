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
import com.project.socialmediaplatform.model.SearchModel;
import com.project.socialmediaplatform.model.User;
import com.project.socialmediaplatform.service.PostService;
import com.project.socialmediaplatform.service.UserService;
import org.springframework.web.bind.annotation.RequestParam;




@RestController
@RequestMapping("/api/user")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;
    
     //createpost
     @PostMapping("/{userId}/post")
     public ResponseEntity<Post> createPost(@PathVariable Long userId, @RequestBody Post newPost) {
         User user = userService.getUserById(userId);
         Post createdPost = postService.createPost(user, newPost);
         return ResponseEntity.ok(createdPost);
     }
     //deletepost
     @DeleteMapping("/post/{postId}")
     public ResponseEntity<String> deletePost(@PathVariable Long postId) {
         postService.deletePost(postId);
         return ResponseEntity.ok("Deleted Successfully");
     }
     
     //editpost
     @PutMapping("/post/{postId}")
     public ResponseEntity<Post> editPost(@PathVariable Long postId, @RequestBody String updatedCaption) {
         Post editedPost = postService.editPost(postId, updatedCaption);
         return ResponseEntity.ok(editedPost);
     }
     
     //viewposts
     @GetMapping("/{userId}/post")
     public ResponseEntity<List<Post>> getOtherUserPosts(@PathVariable Long userId) {
         List<Post> otherUserPosts = userService.getOtherUserPosts(userId);
         return ResponseEntity.ok(otherUserPosts);
     }

    //  @GetMapping("/post/search")
    //  public ResponseEntity<List<Post>> searchPost(@RequestParam("search") SearchModel searchModel) {
    //     List<Post> searchResults = postService.searchPost(searchModel);
    //     return ResponseEntity.ok(searchResults);
    //  }   

}



