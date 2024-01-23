package com.project.socialmediaplatform.service;

import java.security.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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
        Friend existingFriendship = friendListRepo.findByUserIdAndFriendIdAndIsActiveTrue(sender, receiver);
        int count;

        if (existingFriendship != null) {
            Friend friend = existingFriendship;
            if (friend.getStatusId() == 2) {
                count = friend.getCount() + 1;
            } else {
                count = 1;
            }
        } else {
            count = 1;
        }

        Friend friendRequest = new Friend();

        friendRequest.setUserId(sender);
        friendRequest.setFriendId(receiver);
        friendRequest.setStatusId(0);
        friendRequest.setCount(count);
        friendRequest.setRequestTime(new Date());
        friendRequest.setModifiedTime(new Date());
        friendRequest.setActive(true);
        friendListRepo.save(friendRequest);
        return friendRequest;
    }

    public Friend acceptFriendRequest(Long userId, Long friendId) {
        User user1 = userRepo.findByUserId(userId);
        User user2 = userRepo.findByUserId(friendId);
        Friend friend = friendListRepo.findByUserIdAndFriendIdAndIsActiveTrue(user1, user2);
        if (friend != null) {
            if (friend.getStatusId() == 0) {
                friend.setActive(true);
                friend.setStatusId(1);
                friend.setModifiedTime(new Date());
            }
            return friendListRepo.save(friend);
        }
        else throw new NoSuchElementException("No record found");
    }

    public Friend rejectFriendRequest(Long userId, Long friendId) {
        User user1 = userRepo.findByUserId(userId);
        User user2 = userRepo.findByUserId(friendId);
        Friend friend = friendListRepo.findByUserIdAndFriendIdAndIsActiveTrue(user1, user2);
        friend.setStatusId(2);
        friend.setActive(false);
        return friendListRepo.save(friend);
    }

    public List<Friend> seePendingFriendRequests(Long userId) {
        User user = userRepo.findByUserId(userId);
        return friendListRepo.findByFriendIdAndStatusId(user, 0);
    }

    public List<Friend> getFriends(Long userId) {
        User user = userRepo.findByUserId(userId);
        return friendListRepo.findByStatusIdAndFriendIdOrUserId(1, user, user);
    }

}
