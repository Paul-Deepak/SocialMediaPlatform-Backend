package com.project.socialmediaplatform.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "friendlist")
public class Friend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "friendlist_id")
    private Long friendListId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User userId;

    @ManyToOne
    @JoinColumn(name = "friend_id", nullable = false)
    private User friendId;

    @Column(name = "status", nullable = false, length = 20)
    private String status; // PENDING,ACCEPTED,REJECTED

    @Column(name = "request_time", nullable = false)
    private Date requestTime;

    @Column(name = "modified_time")
    private Date modifiedTime;

    @ManyToOne
    @JoinColumn(name = "request_sent_by")
    private User requestSentBy;

    public Friend() {
    }

    public Friend(Long friendListId, User userId, User friendId, String status, Date requestTime, Date modifiedTime,
            User requestSentBy) {
        this.friendListId = friendListId;
        this.userId = userId;
        this.friendId = friendId;
        this.status = status;
        this.requestTime = requestTime;
        this.modifiedTime = modifiedTime;
        this.requestSentBy = requestSentBy;
    }

    public Long getFriendListId() {
        return friendListId;
    }

    public void setFriendListId(Long friendListId) {
        this.friendListId = friendListId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User user) {
        this.userId = user;
    }

    public User getFriendId() {
        return friendId;
    }

    public void setFriendId(User friend) {
        this.friendId = friend;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Date requestTime) {
        this.requestTime = requestTime;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public User getRequestSentBy() {
        return requestSentBy;
    }

    public void setRequestSentBy(User requestSentBy) {
        this.requestSentBy = requestSentBy;
    }

}

