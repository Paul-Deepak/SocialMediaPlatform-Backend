package com.project.socialmediaplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.socialmediaplatform.model.Like;
import com.project.socialmediaplatform.model.LikeKey;

public interface LikeRepo extends JpaRepository<Like,LikeKey>{
    
}
