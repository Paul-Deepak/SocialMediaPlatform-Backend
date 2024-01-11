package com.project.socialmediaplatform.service;

import java.sql.Timestamp;
import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.socialmediaplatform.model.Like;
import com.project.socialmediaplatform.model.LikeKey;
import com.project.socialmediaplatform.repository.LikeRepo;

@Service
public class LikesService {

    @Autowired
    private LikeRepo likeRepo;

    public Like addLikeForPost(Long userId, String type, Long postId) {
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
