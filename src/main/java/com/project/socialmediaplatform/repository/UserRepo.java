package com.project.socialmediaplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.socialmediaplatform.model.User;

public interface UserRepo extends JpaRepository<User,Long>{
    
}
