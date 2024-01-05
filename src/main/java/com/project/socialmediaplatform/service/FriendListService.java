package com.project.socialmediaplatform.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.project.socialmediaplatform.model.Friend;
import com.project.socialmediaplatform.model.User;
import com.project.socialmediaplatform.repository.FriendListRepo;

public class FriendListService {
    @Autowired
    private FriendListRepo friendlistrepo;
    public void sendFriendRequest(User sender, User receiver) {
       
        Friend friendRequest = new Friend();
        friendRequest.setUserId(sender);
        friendRequest.setFriendId(receiver);
        friendRequest.setStatus("PENDING");
        friendRequest.setRequestTime(new Date());
        friendRequest.setRequestSentBy(sender);
        friendlistrepo.save(friendRequest);
    }
}
