package com.project.socialmediaplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.socialmediaplatform.model.Comment;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Long> {

    @Query("select c from Comment c where c.commentId = :commentId AND c.isDeleted = false")
    Comment findByCommentId(Long commentId);
}
