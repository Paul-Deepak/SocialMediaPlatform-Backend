package com.project.socialmediaplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.socialmediaplatform.model.Like;

public interface LikeRepo extends JpaRepository<Like,Long>{
    
}
