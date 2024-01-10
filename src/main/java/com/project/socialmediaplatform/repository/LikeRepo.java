package com.project.socialmediaplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.socialmediaplatform.model.Like;
import com.project.socialmediaplatform.model.LikeKey;

@Repository
public interface LikeRepo extends JpaRepository<Like,LikeKey>{
    
}
