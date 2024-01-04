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
    
}
