package com.project.socialmediaplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.socialmediaplatform.model.Post;
import com.project.socialmediaplatform.model.User;

import java.util.List;

@Repository
public interface PostRepo extends JpaRepository<Post,Long>{
    List<Post> findByPostId(Long postId);
    List<Post> findByUserNot(User user);
    List<Post> findByUser(User user);
    
    // @Query("select p from post p where p.postId = :postId and p.isDeleted = 0")
    // List<Post> findByPostIdAndisDeleted(@Param("postId") Long postId);
}
