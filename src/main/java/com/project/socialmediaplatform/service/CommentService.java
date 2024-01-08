package com.project.socialmediaplatform.service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.socialmediaplatform.model.Comment;
import com.project.socialmediaplatform.model.Post;
import com.project.socialmediaplatform.model.User;
import com.project.socialmediaplatform.repository.CommentRepo;
import com.project.socialmediaplatform.repository.PostRepo;
import com.project.socialmediaplatform.repository.UserRepo;

import jakarta.transaction.Transactional;

@Service
public class CommentService {
    
 @Autowired
    private CommentRepo commentRepo;
 @Autowired
    private UserRepo userRepo;
@Autowired
private PostRepo postRepo;
    @Autowired
    private PostService postService;
    
    // public Comment addComment(Long postId, Long userId, String commentText) {
    //     Post post=null;
    //     try{
    //         post = postService.getPostById(postId);
    //     }
    //     catch(Exception E){
    //         E.printStackTrace();
    //     }
        
    //     Comment comment = new Comment();
    //     comment.setPost(post);
    //     comment.setUser(userRepo.findById(userId)
    //             .orElseThrow(() -> new UsernameNotFoundException("User not found with ID: " + userId)));
    //     comment.setCommentText(commentText);
    //     comment.setCommentedOn(Timestamp.from(Instant.now()));
    //     comment.setLastModifiedOn(Timestamp.from(Instant.now()));
    //     return commentRepo.save(comment);
    // }
    public Comment addComment(long userId, String newComment, long postId) {
        User user=userRepo.findByUserId(userId);
        Post post=postRepo.findById(postId).orElse(null);
        if(user==null){
            return null;
        }
        if(post==null){
            return null;
        }
        Comment comment =new Comment();
        comment.setPost(post);
        comment.setUser(user);
        comment.setCommentText(newComment);
        comment.setCommentedOn(Timestamp.from(Instant.now()));
        comment.setLastModifiedOn(Timestamp.from(Instant.now()));
        return commentRepo.save(comment);
    }

    public Comment editComment(Long commentId,String updatedComment){
        commentRepo.findById(commentId).ifPresent(comment -> {
            comment.setCommentText(updatedComment);
            commentRepo.save(comment);
        });
        return null;
    }

    public void deleteComment(Long commentId){
        commentRepo.findById(commentId).ifPresent(comment -> {  
            commentRepo.delete(comment);
        });
    }
        


}
