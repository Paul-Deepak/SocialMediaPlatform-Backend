package com.project.socialmediaplatform.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.socialmediaplatform.model.Friend;
import com.project.socialmediaplatform.model.User;
import com.project.socialmediaplatform.repository.FriendListRepo;
import com.project.socialmediaplatform.repository.UserRepo;

@Service
public class FriendListService {

    @Autowired
    private FriendListRepo friendListRepo;

    @Autowired
    private UserRepo userRepo; 

    public Friend sendFriendRequest(User sender, User receiver) {
        Friend friendRequest = new Friend();
        friendRequest.setUserId(sender);
        friendRequest.setFriendId(receiver);
        friendRequest.setStatusId(0);
        friendRequest.setRequestTime(new Date());
        friendRequest.setModifiedTime(new Date());
        friendListRepo.save(friendRequest);
        return friendRequest;
    }

    public Friend acceptFriendRequest(Long userId,Long friendId){    
        User user1=userRepo.findById(userId).get();
        User user2=userRepo.findById(friendId).get();
        Friend friend=friendListRepo.findByUserIdAndFriendId(user1,user2).get(0);
        friend.setStatusId(1);
        return friendListRepo.save(friend);
    }
    
    public Friend rejectFriendRequest(Long userId,Long friendId){    
        User user1=userRepo.findById(userId).get();
        User user2=userRepo.findById(friendId).get();
        Friend friend=friendListRepo.findByUserIdAndFriendId(user1,user2).get(0);
        friend.setStatusId(2);
        return friendListRepo.save(friend);
    }

    public List<Friend> seePendingFriendRequests(Long userId) {
        User user=userRepo.findByUserId(userId);
        return friendListRepo.findByFriendIdAndStatusId(user, 0);
    }

    public List<Friend> getFriends(Long userId) {
        User user=userRepo.findByUserId(userId);
        return friendListRepo.findByStatusIdAndFriendIdOrUserId(1,user,user);
    }

}
