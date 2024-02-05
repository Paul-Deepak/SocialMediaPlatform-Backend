package com.project.socialmediaplatform.service;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.socialmediaplatform.Exception.FriendRequestAlreadySentException;
import com.project.socialmediaplatform.Exception.FriendRequestNotFoundException;
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
                friend.setActive(false);
                count = friend.getCount() + 1;
            } else {
                throw new FriendRequestAlreadySentException("Friend Request Already sent");
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
                return friendListRepo.save(friend);
            } else {
                throw new FriendRequestNotFoundException("No Pending Friend Request with this user found");
            }
        } else {
            throw new FriendRequestNotFoundException("Friend Request Not found");
        }
    }

    public Friend rejectFriendRequest(Long userId, Long friendId) {
        User user1 = userRepo.findByUserId(userId);
        User user2 = userRepo.findByUserId(friendId);
        Friend friend = friendListRepo.findByUserIdAndFriendIdAndIsActiveTrue(user1, user2);
        if (friend != null) {
            if (friend.getStatusId() == 0) {
                friend.setStatusId(2);
                friend.setModifiedTime(new Date());
                friend.setActive(true);
                return friendListRepo.save(friend);
            } else {
                throw new FriendRequestNotFoundException("No Pending Friend Request with this user found");
            }
        } else {
            throw new FriendRequestNotFoundException("Friend Request Not found");
        }
    }

    public List<Friend> seePendingFriendRequests(Long userId) {
        User user = userRepo.findByUserId(userId);
        return friendListRepo.findByFriendIdAndStatusIdAndIsActiveTrue(user, 0);
    }

    public List<Friend> getFriends(Long userId) {
        return friendListRepo.findByUserIdAndStatusId(userId, 1);
    }

}
