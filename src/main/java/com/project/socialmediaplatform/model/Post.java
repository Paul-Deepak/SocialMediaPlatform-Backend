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

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long postId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Lob
    @Column(name = "content")
    private byte[] content;

    @Column(name = "post_caption")
    private String caption;

    @Column(name = "created_on")
    private Timestamp postedOn;

    @Column(name = "last_modified_on")
    private Timestamp lastModifiedOn;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    public Post() {
    }

    public Post(Long postId, User user, byte[] content,String caption, Timestamp postedOn, Timestamp lastModifiedOn,boolean isDeleted) {
        this.postId = postId;
        this.user = user;
        this.content = content;
        this.caption = caption;
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

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

}