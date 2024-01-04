package com.project.socialmediaplatform.model;


import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "likes")
public class Like {

    @EmbeddedId
    private LikeKey id;

    @Column(name = "liked_on")
    private Timestamp likedOn;

    @Column(name = "last_modified_on")
    private Timestamp lastModifiedOn;

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

}