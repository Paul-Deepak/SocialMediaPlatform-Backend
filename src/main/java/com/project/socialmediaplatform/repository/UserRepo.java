package com.project.socialmediaplatform.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.project.socialmediaplatform.model.User;





public interface UserRepo extends JpaRepository<User,Long>{

    User findByEmail(String email);
    User findByUserId(Long userId);

    
}
