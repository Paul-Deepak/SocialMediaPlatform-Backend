package com.project.socialmediaplatform.service;

import java.sql.Timestamp;
import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.socialmediaplatform.Exception.CommentNotFoundException;
import com.project.socialmediaplatform.Exception.PostNotFoundException;
import com.project.socialmediaplatform.Exception.UserNotFoundException;
import com.project.socialmediaplatform.model.Comment;
import com.project.socialmediaplatform.model.Post;
import com.project.socialmediaplatform.model.User;
import com.project.socialmediaplatform.repository.CommentRepo;
import com.project.socialmediaplatform.repository.PostRepo;
import com.project.socialmediaplatform.repository.UserRepo;

@Service
public class CommentService {

    @Autowired
    private CommentRepo commentRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PostRepo postRepo;

    // public Comment addComment(Long postId, Long userId, String commentText) {
    // Post post=null;
    // try{
    // post = postService.getPostById(postId);
    // }
    // catch(Exception E){
    // E.printStackTrace();
    // }

    // Comment comment = new Comment();
    // comment.setPost(post);
    // comment.setUser(userRepo.findById(userId)
    // .orElseThrow(() -> new UsernameNotFoundException("User not found with ID: " +
    // userId)));
    // comment.setCommentText(commentText);
    // comment.setCommentedOn(Timestamp.from(Instant.now()));
    // comment.setLastModifiedOn(Timestamp.from(Instant.now()));
    // return commentRepo.save(comment);
    // }
    public Comment addComment(long userId, String newComment, long postId) {
        User user = userRepo.findByUserId(userId);
        Post post = postRepo.findById(postId).orElse(null);
        if (user == null) {
            throw new UserNotFoundException("No such User exists");
        }
        if (post == null) {
            throw new PostNotFoundException("No such Post exists");
        }
        Comment comment = new Comment();
        comment.setPostId(post);
        comment.setUserId(user);
        comment.setCommentText(newComment);
        comment.setCreatedOn(Timestamp.from(Instant.now()));
        comment.setLastModifiedOn(Timestamp.from(Instant.now()));
        return commentRepo.save(comment);
    }

    public Comment editComment(Long commentId, String updatedComment) {
        Comment comment = commentRepo.findByCommentId(commentId);
        if (comment == null)
            throw new CommentNotFoundException("No such Comment exists");
        comment.setCommentText(updatedComment);
        comment.setLastModifiedOn(Timestamp.from(Instant.now()));
        return commentRepo.save(comment);
    }

    public Comment deleteComment(Long commentId) {
        Comment comment = commentRepo.findByCommentId(commentId);
        if (comment == null)
            throw new CommentNotFoundException("No such Comment exists");
        comment.setDeleted(true);
        comment.setLastModifiedOn(Timestamp.from(Instant.now()));
        commentRepo.save(comment);
        return comment;
    }
}
