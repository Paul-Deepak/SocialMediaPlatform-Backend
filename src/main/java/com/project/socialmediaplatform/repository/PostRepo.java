package com.project.socialmediaplatform.repository;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.socialmediaplatform.model.Post;
import com.project.socialmediaplatform.model.User;

import java.util.List;

@Repository
public interface PostRepo extends JpaRepository<Post, Long>, JpaSpecificationExecutor<Post> {

        @Query("select p from Post p where p.postId = :postId AND p.isDeleted = false")
        Post findByPostId(Long postId);

        @Query("select p from Post p where p.user = :user AND p.isDeleted = false")
        List<Post> findByUser(User user);

        List<Post> findAll(Specification specs);

        // @Query("select p from post p where p.postId = :postId and p.isDeleted = 0")
        // List<Post> findByPostIdAndisDeleted(@Param("postId") Long postId);
}
