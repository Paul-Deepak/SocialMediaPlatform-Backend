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


    public Like addLikeForPost(Long postId, Long userId) {
        LikeKey likekey = new LikeKey();
        likekey.setUserId(userId);
        likekey.setLikeType("post");
        likekey.setTypeId(postId);

        Like like = new Like();
        like.setId(likekey);
        like.setLastModifiedOn(Timestamp.from(Instant.now()));
        like.setLikedOn(Timestamp.from(Instant.now()));

        return likeRepo.save(like);

    }

    public void removeLikeForPost(LikeKey likeKey) {
        LikeKey likeId = new LikeKey(likeKey.getUserId(), likeKey.getLikeType(), likeKey.getTypeId());
        likeRepo.deleteById(likeId);
    }

    public Like addLikeForComment(Long userId, Long commentId) {
        LikeKey likekey = new LikeKey();
        likekey.setUserId(userId);
        likekey.setLikeType("comment");
        likekey.setTypeId(commentId);

        Like like = new Like();
        like.setId(likekey);
        like.setLastModifiedOn(Timestamp.from(Instant.now()));
        like.setLikedOn(Timestamp.from(Instant.now()));

        return likeRepo.save(like);
    }

    public void removeLikeForComment(LikeKey likeKey) {
        LikeKey likeId = new LikeKey(likeKey.getUserId(), likeKey.getLikeType(), likeKey.getTypeId());
        likeRepo.deleteById(likeId);
    }

}
