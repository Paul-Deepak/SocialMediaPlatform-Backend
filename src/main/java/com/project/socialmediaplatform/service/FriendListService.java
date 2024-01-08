package com.project.socialmediaplatform.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.socialmediaplatform.model.Friend;
import com.project.socialmediaplatform.model.User;
import com.project.socialmediaplatform.repository.FriendListRepo;

@Service
public class FriendListService {

    @Autowired
    private FriendListRepo friendListRepo;

    public void sendFriendRequest(User sender, User receiver) {
        Friend friendRequest = new Friend();
        friendRequest.setUserId(sender);
        friendRequest.setFriendId(receiver);
        friendRequest.setStatus("PENDING");
        friendRequest.setRequestTime(new Date());
        friendRequest.setRequestSentBy(sender);
        friendListRepo.save(friendRequest);
    }

    public void acceptFriendRequest(){
    }

    public List<User> seePendingFriendRequests(String email) {
        return null;
    }

    public List<User> getFriends(String email) {
        return null;
    }

}
