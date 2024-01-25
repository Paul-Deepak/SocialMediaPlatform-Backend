package com.project.socialmediaplatform.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.socialmediaplatform.Exception.AuthenticationException;
import com.project.socialmediaplatform.Exception.UserAlreadyExistsException;
import com.project.socialmediaplatform.Exception.UserNotFoundException;
import com.project.socialmediaplatform.model.Post;
import com.project.socialmediaplatform.model.SearchModel;
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
        if (userRepo.findByEmail(user.getEmail()) != null) {
            throw new UserAlreadyExistsException("Email " + user.getEmail() + " already exists");
        }
        // User checkUser = userRepo.findByEmailId(user.getEmail());
        // if (checkUser != null && checkUser.isActive() == false) {
        // return userRepo.insertUserIgnoreDuplicate(user.getUserName(),
        // user.getEmail(),
        // new BCryptPasswordEncoder().encode(user.getPassword()), user.getProfilePic(),
        // user.getBio(),
        // new Date(), new Date(), true);

        // }
        user.setUserId(null);
        user.setCreatedDate(new Date());
        user.setActive(true);
        user.setModifiedDate(new Date());
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        return userRepo.save(user);
    }

    public User authenticateUser(String email, String password) {
        User user = userRepo.findByEmail(email);
        if (user == null)
            throw new AuthenticationException("Invalid Email");

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (passwordEncoder.matches(password, user.getPassword())) {
            return user;
        } else {
            throw new AuthenticationException(
                    "Invalid credentials   " + " ******* " + user.getPassword());
        }
    }

    public User updateUser(Long userId, User updatedUser) {
        User user = userRepo.findByUserId(userId);
        if (user == null) {
            throw new UserNotFoundException("No such User exists");
        }
        if (updatedUser.getUserName() != null)
            user.setUserName(updatedUser.getUserName());
        if (updatedUser.getProfilePic() != null)
            user.setProfilePic(updatedUser.getProfilePic());
        if (updatedUser.getBio() != null)
            user.setBio(updatedUser.getBio());
        if (updatedUser.getModifiedDate() != null)
            user.setModifiedDate(new Date());
        if (updatedUser.getPassword() != null)
            user.setPassword(new BCryptPasswordEncoder().encode(updatedUser.getPassword()));
        return userRepo.save(user);
    }

    public void deactivateUser(Long userId) {
        User user = userRepo.findByUserId(userId);
        if (user == null) {
            throw new UserNotFoundException("No such User exists");
        }
        user.setActive(false);
        userRepo.save(user);
    }

    public List<Post> getOtherUserPosts(Long userId) {
        User user = userRepo.findByUserId(userId);
        if (user == null) {
            throw new UserNotFoundException("No such User Exists");
        }
        List<Post> otherUserPosts = postRepo.findByUser(user);
        return otherUserPosts;
    }

    // for searching
    public User getUserByEmail(String email) {
        User user = userRepo.findByEmail(email);
        if (user == null) {
            throw new UserNotFoundException("No Such User Exists");
        }
        return user;
    }

    public List<User> searchUserWithFilter(SearchModel searchModel) {
        return userRepo.findAllByUserName(searchModel.getSearchWord());
    }

}
