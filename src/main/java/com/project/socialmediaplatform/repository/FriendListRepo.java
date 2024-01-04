package com.project.socialmediaplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.socialmediaplatform.model.Friend;

public interface FriendListRepo extends JpaRepository<Friend,Long>{
    
}
