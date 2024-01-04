package com.project.socialmediaplatform.controller;


import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.project.socialmediaplatform.model.Post;
import com.project.socialmediaplatform.model.User;
import com.project.socialmediaplatform.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/api/user/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        User registeredUser = userService.registerUser(user);
        return ResponseEntity.ok(registeredUser);
    }
     @PostMapping(value = "/api/user/post")
    public String postContent(@RequestBody Map<String, String> postMap) {
        System.out.println(postMap);
        String email = postMap.get("email");
        String postContent = postMap.get("postContent");
        return this.userService.postContent(email, postContent);
    }

    @PostMapping(value = "/api/user/userPosts")
    public List<Post> getUserPosts(@RequestBody Map<String, String> userPostsRequests) {
        String email = userPostsRequests.get("email");
        return this.userService.getUserPosts(email);
    }
    // @PostMapping(value = "/api/user/sendFriendRequest")
    // public String sendFriendRequest(@RequestBody Map<String, String> requestMap) {
    //     String email = requestMap.get("email");
    //     Long friendId = Long.parseLong(requestMap.get("friendId"));
    //     System.out.println(email + " " + friendId);
    //     return this.userService.sendFriendRequest(email, friendId);
    // }

    @PostMapping(value = "/api/user/friends")
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
    //     return new User((long) 1, "paul", "abc@gmail.com",
    //      "abccc", null, null, new Date(), null, false);
        
    // }



}
