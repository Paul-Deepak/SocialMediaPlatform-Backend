package com.project.socialmediaplatform.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class LikeKey implements Serializable {

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "like_type", length = 8, nullable = false)
    private String likeType;

    @Column(name = "post/comment_id")
    private Long likedId;
    
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getLikeType() {
        return likeType;
    }

    public void setLikeType(String likeType) {
        this.likeType = likeType;
    }

    public Long getLikedId() {
        return likedId;
    }

    public void setLikedId(Long likedId) {
        this.likedId = likedId;
    }

    public LikeKey(Long userId, String likeType, Long likedId) {
        this.userId = userId;
        this.likeType = likeType;
        this.likedId = likedId;
    }

    public LikeKey() {
    }
    
}
