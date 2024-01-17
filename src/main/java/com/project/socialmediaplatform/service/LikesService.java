package com.project.socialmediaplatform.service;

import java.sql.Timestamp;
import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.socialmediaplatform.Exception.CommentNotFoundException;
import com.project.socialmediaplatform.Exception.PostNotFoundException;
import com.project.socialmediaplatform.Exception.UserNotFoundException;
import com.project.socialmediaplatform.model.Comment;
import com.project.socialmediaplatform.model.Like;
import com.project.socialmediaplatform.model.LikeKey;
import com.project.socialmediaplatform.model.Post;
import com.project.socialmediaplatform.model.User;
import com.project.socialmediaplatform.repository.CommentRepo;
import com.project.socialmediaplatform.repository.LikeRepo;
import com.project.socialmediaplatform.repository.PostRepo;
import com.project.socialmediaplatform.repository.UserRepo;

@Service
public class LikesService {

    @Autowired
    private LikeRepo likeRepo;

    @Autowired 
    private UserRepo userRepo;

    @Autowired 
    private PostRepo postRepo;

    @Autowired 
    private CommentRepo commentRepo;

    public Like addLikeForPost(Long userId, String type, Long postId) {

        User user = userRepo.findByUserId(userId);
        Post post = postRepo.findByPostId(postId);
        if(user==null) throw new UserNotFoundException("No such user exists");
        if(post==null) throw new PostNotFoundException("No such post exists");

        LikeKey likekey = new LikeKey();
        likekey.setUserId(userId);
        likekey.setLikeType(type);
        likekey.setTypeId(postId);
        Like like = new Like();
        like.setId(likekey);
        like.setLastModifiedOn(Timestamp.from(Instant.now()));
        like.setLikedOn(Timestamp.from(Instant.now()));

        return likeRepo.save(like);

    }

    public Like addLikeForComment(Long userId, String type, Long commentId) {

        User user = userRepo.findByUserId(userId);
        Comment comment = commentRepo.findByCommentId(commentId);
        if(user==null) throw new UserNotFoundException("No such user exists");
        if(comment==null) throw new CommentNotFoundException("No such comment exists");

        LikeKey likekey = new LikeKey();
        likekey.setUserId(userId);
        likekey.setLikeType(type);
        likekey.setTypeId(commentId);

        Like like = new Like();
        like.setId(likekey);
        like.setLastModifiedOn(Timestamp.from(Instant.now()));
        like.setLikedOn(Timestamp.from(Instant.now()));

        return likeRepo.save(like);
    }

    public void removeLikeForPost(Long userId, String type, Long postId) {
        LikeKey likeId = new LikeKey(userId, type, postId);
        likeRepo.deleteById(likeId);
    }

    public void removeLikeForComment(Long userId, String type, Long commentId) {
        LikeKey likeId = new LikeKey(userId, type, commentId);
        likeRepo.deleteById(likeId);
    }

}
