package com.project.socialmediaplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.socialmediaplatform.model.Post;
import com.project.socialmediaplatform.model.User;

import java.util.List;


public interface PostRepo extends JpaRepository<Post,Long>{
    List<Post> findByPostId(Long postId);
    List<Post> findByUserNot(User user);
}
