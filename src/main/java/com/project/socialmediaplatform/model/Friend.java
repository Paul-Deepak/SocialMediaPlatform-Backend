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
    @JoinColumn(name = "request_sent_by")
    private User userId;

    @ManyToOne
    @JoinColumn(name = "request_sent_to")
    private User friendId;

    @Column(name = "status_id")
    private int statusId; // PENDING(0),ACCEPTED(1),REJECTED(2),UNFRIEND(3)

    @Column(name = "request_time")
    private Date requestTime;

    @Column(name = "modified_time")
    private Date modifiedTime;

    public Friend() {
    }

    public Friend(Long friendListId, User userId, User friendId, int statusId, Date requestTime, Date modifiedTime,
            User requestSentBy) {
        this.friendListId = friendListId;
        this.userId = userId;
        this.friendId = friendId;
        this.statusId = statusId;
        this.requestTime = requestTime;
        this.modifiedTime = modifiedTime;
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

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

}

