package com.project.socialmediaplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.socialmediaplatform.model.Post;

public interface PostRepo extends JpaRepository<Post,Long>{
    
}
