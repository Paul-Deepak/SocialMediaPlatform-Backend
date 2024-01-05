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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/user/")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @Autowired
    private CommentService commentService;
    

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        User registeredUser = userService.registerUser(user);
        return ResponseEntity.ok(registeredUser);
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable Long userId, @RequestBody User updatedUser) {
        User updatedUserInfo = userService.updateUser(userId, updatedUser);
        return ResponseEntity.ok(updatedUserInfo);
    }

    //createpost
    @PostMapping("post/{userId}")
    public ResponseEntity<Post> createPost(@PathVariable Long userId, @RequestBody byte[] postContent) {
        User user = userService.getUserById(userId);
        Post createdPost = postService.createPost(user, postContent);
        return ResponseEntity.ok(createdPost);
    }
    //deletepost
    @DeleteMapping("post/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
        return ResponseEntity.noContent().build();
    }
    
    //editpost
    @PutMapping("/post/{postId}")
    public ResponseEntity<Post> editPost(@PathVariable Long postId, @RequestBody byte[] updatedContent) {
        Post editedPost = postService.editPost(postId, updatedContent);
        return ResponseEntity.ok(editedPost);
    }
    
    //viewposts
    @GetMapping("seeposts/{userId}")
    public ResponseEntity<List<Post>> getOtherUserPosts(@PathVariable Long userId) {
        List<Post> otherUserPosts = userService.getOtherUserPosts(userId);
        System.out.println(otherUserPosts);
        return ResponseEntity.ok(otherUserPosts);
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


    // @PostMapping(value = "/post")
    // public String postContent(@RequestBody Map<String, String> postMap) {
    // System.out.println(postMap);
    // String email = postMap.get("email");
    // String postContent = postMap.get("postContent");
    // return this.userService.postContent(email, postContent);
    // }

    // @PostMapping(value = "/api/user/sendFriendRequest")
    // public String sendFriendRequest(@RequestBody Map<String, String> requestMap)
    // {
    // String email = requestMap.get("email");
    // Long friendId = Long.parseLong(requestMap.get("friendId"));
    // System.out.println(email + " " + friendId);
    // return this.userService.sendFriendRequest(email, friendId);
    // }

    @PostMapping("/friends")
    public List<User> friends(@RequestBody Map<String, String> requestMap) {
        String email = requestMap.get("email");
        // System.out.println(email);
        return this.userService.getFriends(email);
    }

    @PostMapping(value = "/api/user/pendingFriendRequest")
    public List<User> pendingFriendRequest(@RequestBody Map<String, String> requestMap) {
        String email = requestMap.get("email");
        // System.out.println(email);
        return this.userService.pendingFriendRequest(email);
    }

    // @GetMapping("{UserId}")
    // public User getUser(Long userId){
    // return new User((long) 1, "paul", "abc@gmail.com",
    // "abccc", null, null, new Date(), null, false);

    // }

}
