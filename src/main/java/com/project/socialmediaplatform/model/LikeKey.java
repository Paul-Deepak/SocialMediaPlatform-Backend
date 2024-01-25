package com.project.socialmediaplatform.model;

import jakarta.persistence.Column;

// @Embeddable
public class LikeKey {

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "type")
    private String likeType;

    @Column(name = "type_id")
    private Long typeId;

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

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public LikeKey(Long userId, String likeType, Long typeId) {
        this.userId = userId;
        this.likeType = likeType;
        this.typeId = typeId;
    }

    public LikeKey() {
    }

}
