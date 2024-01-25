package com.project.socialmediaplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.socialmediaplatform.model.Friend;
import com.project.socialmediaplatform.model.User;
import java.util.List;


@Repository
public interface FriendListRepo extends JpaRepository<Friend, Long> {

    // @Query("select friendListId from friend where userId = :userId and friendId =
    // :friendId")
    Friend findByUserIdAndFriendId(User userId, User friendId);


    Friend findByUserIdAndFriendIdAndIsActiveTrue(User userId, User friendId);

    
    List<Friend> findByStatusIdAndFriendIdOrUserId(int statusId, User user, User user2);
    
    
    // pending req
    List<Friend> findByFriendIdAndStatusIdAndIsActiveTrue(User user, int i);

    // @Query(value= "select COALESCE(COUNT(*), 0) from friendlist where req_sent_by = :#{#user.userId} and req_sent_to = :#{#user.friendId}",nativeQuery = true)
    // int getCount(@Param("user") User userId,@Param("friend") User friendId);

}
