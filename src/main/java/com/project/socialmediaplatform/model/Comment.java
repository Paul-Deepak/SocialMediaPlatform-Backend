package com.project.socialmediaplatform.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.sql.Timestamp;

@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long commentId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userId;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post postId;

    @ManyToOne
    @JoinColumn(name = "parent_comment_id")
    private Comment parentId;

    @Column(name = "comment_text")
    private String commentText;

    @Column(name = "created_on")
    private Timestamp createdOn;

    @Column(name = "last_modified_on")
    private Timestamp lastModifiedOn;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    public Comment() {
    }

    public Comment(Long commentId, User userId, Post postId, Comment parentId, String commentText, Timestamp createdOn,
            Timestamp lastModifiedOn, boolean isDeleted) {
        this.commentId = commentId;
        this.userId = userId;
        this.postId = postId;
        this.parentId = parentId;
        this.commentText = commentText;
        this.createdOn = createdOn;
        this.lastModifiedOn = lastModifiedOn;
        this.isDeleted = isDeleted;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public Post getPostId() {
        return postId;
    }

    public void setPostId(Post postId) {
        this.postId = postId;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public Timestamp getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Timestamp createdOn) {
        this.createdOn = createdOn;
    }

    public Timestamp getLastModifiedOn() {
        return lastModifiedOn;
    }

    public void setLastModifiedOn(Timestamp lastModifiedOn) {
        this.lastModifiedOn = lastModifiedOn;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Comment getParentId() {
        return parentId;
    }

    public void setParentId(Comment parentId) {
        this.parentId = parentId;
    }

}
