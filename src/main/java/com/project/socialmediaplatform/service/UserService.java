package com.project.socialmediaplatform.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.socialmediaplatform.model.Post;
import com.project.socialmediaplatform.model.User;
import com.project.socialmediaplatform.repository.UserRepo;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public User registerUser(User user) {
        user.setCreatedDate(new Date());
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        return userRepo.save(user);
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public User getUserById(Long userId) {
        return userRepo.findById(userId).get();
    }

    public User updateUser(Long userId, User updatedUser) {
        return userRepo.findById(userId)
                .map(user -> {
                    user.setUserName(updatedUser.getUserName());
                    user.setEmail(updatedUser.getEmail());
                    user.setProfilePic(updatedUser.getProfilePic());
                    user.setBio(updatedUser.getBio());
                    user.setModifiedDate(new Date());
                    return userRepo.save(user);
                })
                .orElseThrow(() -> new UsernameNotFoundException("User not found with ID: " + userId));
    }


    public String postContent(String email, String postContent) {
        return "posted successfully";
    }

    
    public void deactivateUser(Long userId) {
        userRepo.findById(userId)
                .ifPresent(user -> {
                    user.setActive(false);
                    userRepo.save(user);
                });
    }

    public List<Post> getUserPosts(String email) {
        return null;
    }

    public List<User> pendingFriendRequest(String email) {
        return null;
    }

    public List<User> getFriends(String email) {
        return null;
    }

    public String sendFriendRequest(String email, Long friendId) {
        return null;
    }
}
