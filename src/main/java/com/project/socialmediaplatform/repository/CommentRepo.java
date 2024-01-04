package com.project.socialmediaplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.socialmediaplatform.model.Comment;

public interface CommentRepo extends JpaRepository<Comment,Long>{
    
}
