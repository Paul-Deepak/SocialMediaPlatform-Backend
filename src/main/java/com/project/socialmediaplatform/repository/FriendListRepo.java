package com.project.socialmediaplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.socialmediaplatform.model.Friend;
import com.project.socialmediaplatform.model.User;
import java.util.List;

public interface FriendListRepo extends JpaRepository<Friend, Long> {

    // @Query("select friendListId from friend where userId = :userId and friendId =
    // :friendId")
    List<Friend> findByUserIdAndFriendId(User userId, User friendId);

    // pending req
    List<Friend> findByFriendIdAndStatusId(User user, int i);
   

    // List<Friend> findByFriendIdOrUserIdAndStatus(User user, User user2, String status);
    List<Friend> findByStatusIdAndFriendIdOrUserId(int i, User user, User user2);

}
