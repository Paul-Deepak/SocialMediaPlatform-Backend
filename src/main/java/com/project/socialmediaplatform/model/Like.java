package com.project.socialmediaplatform.model;


import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "likes")
public class Like {

    
    private Long likeId;

    @EmbeddedId
    private LikeKey id;

    @Column(name = "created_on")
    private Timestamp likedOn;

    @Column(name = "last_modified_on")
    private Timestamp lastModifiedOn;

    // @Column(name = "user_id")
    // private Long userId;

    // @Column(name = "type")
    // private String likeType;

    // @Column(name = "type_id")
    // private Long typeId;
    
    // public Long getUserId() {
    //     return userId;
    // }

    // public void setUserId(Long userId) {
    //     this.userId = userId;
    // }

    // public String getLikeType() {
    //     return likeType;
    // }

    // public void setLikeType(String likeType) {
    //     this.likeType = likeType;
    // }

    // public Long getTypeId() {
    //     return typeId;
    // }

    // public void setTypeId(Long typeId) {
    //     this.typeId = typeId;
    // }

    public LikeKey getId() {
        return id;
    }

    public void setId(LikeKey id) {
        this.id = id;
    }

    public Timestamp getLikedOn() {
        return likedOn;
    }

    public void setLikedOn(Timestamp likedOn) {
        this.likedOn = likedOn;
    }

    public Timestamp getLastModifiedOn() {
        return lastModifiedOn;
    }

    public void setLastModifiedOn(Timestamp lastModifiedOn) {
        this.lastModifiedOn = lastModifiedOn;
    }

    public Long getLikeId() {
        return likeId;
    }

    public void setLikeId(Long likeId) {
        this.likeId = likeId;
    }

}