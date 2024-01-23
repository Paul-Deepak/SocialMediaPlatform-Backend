package com.project.socialmediaplatform.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.socialmediaplatform.Exception.UserNotFoundException;
import com.project.socialmediaplatform.Exception.ValidationException;
import com.project.socialmediaplatform.model.Comment;
import com.project.socialmediaplatform.model.Post;
import com.project.socialmediaplatform.model.SearchModel;
import com.project.socialmediaplatform.model.User;
import com.project.socialmediaplatform.repository.PostRepo;
import com.project.socialmediaplatform.repository.UserRepo;
import com.project.socialmediaplatform.security.services.UserDetailsImpl;
import com.project.socialmediaplatform.service.PostService;
import com.project.socialmediaplatform.service.UserService;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/user")
public class PostController extends UserManager{

    @Autowired
    private PostService postService;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserService userService;

    @Autowired
    private PostRepo postRepo;

    // createpost
    @PostMapping("/post")
    public ResponseEntity<Post> createPost(@RequestBody Post newPost) {
        User user = getUserFromAuthentication();
        if(user==null)
        throw new UserNotFoundException("no user");
        if (newPost.getContent() == null) {
            throw new ValidationException("Post is Empty");
        }
        if (newPost.getCaption() == null) {
            throw new ValidationException("Caption is required");
        }
        // User user = userRepo.findByUserId(userId);
        Post createdPost = postService.createPost(user, newPost);
        return ResponseEntity.ok(createdPost);
    }

    // deletepost
    @DeleteMapping("/post/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable Long postId) {
        User user = getUserFromAuthentication();
        Post post = postRepo.findByPostId(postId);
        if(post.getUser()==user){
        postService.deletePost(postId);
        return ResponseEntity.ok("Deleted Successfully");
        }
        else throw new AuthorizationServiceException("Unauthorized action");
    }

    // editpost
    @PutMapping("/post/{postId}")
    public ResponseEntity<Post> editPost(@PathVariable Long postId, @RequestBody Post postUpdate) {
        User user = getUserFromAuthentication();
        Post post = postRepo.findByPostId(postId);
        if(post.getUser()==user){
            Post editedPost = postService.editPost(postId, postUpdate.getCaption());
            return ResponseEntity.ok(editedPost);
        }
        else throw new AuthorizationServiceException("Unauthorized action");
    }

    // // viewposts
    // @GetMapping("/{userId}/post")
    // public ResponseEntity<List<Post>> getOtherUserPosts(@PathVariable Long userId) {
    //     List<Post> otherUserPosts = userService.getOtherUserPosts(userId);
    //     return ResponseEntity.ok(otherUserPosts);
    // }

    @GetMapping("/post/search")
    public ResponseEntity<List<Post>> searchPost(@ModelAttribute("search") SearchModel searchModel) { 
        User user = getUserFromAuthentication();
        if(searchModel.getSearchWord()==null && searchModel.getUserId()==null && searchModel.getPostDate()==null){
            return null;
        }  
        List<Post> searchResults = postService.searchPosts(user,searchModel);
        return ResponseEntity.ok(searchResults);
    }

}
