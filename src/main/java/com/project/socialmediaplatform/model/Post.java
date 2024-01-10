package com.project.socialmediaplatform.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long postId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Lob
    @Column(name = "content")
    private byte[] content;

    @Column(name = "created_on")
    private Timestamp postedOn;

    @Column(name = "last_modified_on")
    private Timestamp lastModifiedOn;

    @Column(name = "isdeleted")
    private boolean isDeleted;

    public Post() {
    }

    public Post(Long postId, User user, byte[] content, Timestamp postedOn, Timestamp lastModifiedOn,boolean isDeleted) {
        this.postId = postId;
        this.user = user;
        this.content = content;
        this.postedOn = postedOn;
        this.lastModifiedOn = lastModifiedOn;
        this.isDeleted =isDeleted;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public Timestamp getPostedOn() {
        return postedOn;
    }

    public void setPostedOn(Timestamp date) {
        this.postedOn = date;
    }

    public Timestamp getLastModifiedOn() {
        return lastModifiedOn;
    }

    public void setLastModifiedOn(Timestamp date) {
        this.lastModifiedOn = date;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    

}