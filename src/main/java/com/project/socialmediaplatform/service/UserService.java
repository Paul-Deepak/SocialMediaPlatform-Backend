package com.project.socialmediaplatform.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.socialmediaplatform.model.Post;
import com.project.socialmediaplatform.model.User;
import com.project.socialmediaplatform.repository.PostRepo;
import com.project.socialmediaplatform.repository.UserRepo;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PostRepo postRepo;

    public User registerUser(User user) {
        user.setUserId(null);
        user.setCreatedDate(new Date());
        user.setActive(true);
        user.setModifiedDate(new Date());
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        return userRepo.save(user);
    }

    public User updateUser(Long userId, User updatedUser) {
        return userRepo.findById(userId)
                .map(user -> {
                    if (updatedUser.getUserName()!=null)
                        user.setUserName(updatedUser.getUserName());
                    // if (updatedUser.getEmail() != null)
                    // user.setEmail(updatedUser.getEmail());
                    if (updatedUser.getProfilePic()!=null)
                        user.setProfilePic(updatedUser.getProfilePic());
                    if (updatedUser.getBio()!=null)
                        user.setBio(updatedUser.getBio());
                    if (updatedUser.getModifiedDate()!=null)
                        user.setModifiedDate(new Date());
                    if (updatedUser.getPassword()!=null)
                        user.setPassword(new BCryptPasswordEncoder().encode(updatedUser.getPassword()));
                    return userRepo.save(user);
                }).get();
    }

    public void deactivateUser(Long userId) {
        userRepo.findById(userId).ifPresent(user -> {
                    user.setActive(false);
                    userRepo.save(user);
                });
    }


    public List<Post> getOtherUserPosts(Long userId) {
        User user = userRepo.findById(userId).get();
        List<Post> otherUserPosts = postRepo.findByUser(user);
        return otherUserPosts;
    }

    //for searching
    public User getUserById(Long userId) {
        return userRepo.findById(userId).get();
    }

    //login
    // public User userLogin(User user) {
    //     String Email = user.getEmail();
    //     String Pass = user.getPassword();

    //     User existingUser = userRepo.findByEmail(Email);
    //     if(existingUser.getPassword().equals(new BCryptPasswordEncoder().encode(Pass)) ){
    //         return user;
    //     }
    //     else
    //     return null;
    // }
    
}
