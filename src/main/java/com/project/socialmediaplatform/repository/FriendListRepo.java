package com.project.socialmediaplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.socialmediaplatform.model.Friend;
import com.project.socialmediaplatform.model.User;

public interface FriendListRepo extends JpaRepository<Friend,Long>{
    
    // @Query("select friendListId from friend where userId = :userId and friendId = :friendId")
    // Friend findByUserIdAndFriendId(User userId,User friendId);

}
