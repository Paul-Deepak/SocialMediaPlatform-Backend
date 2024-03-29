package com.project.socialmediaplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.socialmediaplatform.model.Friend;
import com.project.socialmediaplatform.model.User;
import java.util.List;

@Repository
public interface FriendListRepo extends JpaRepository<Friend, Long> {

    Friend findByUserIdAndFriendId(User userId, User friendId);

    Friend findByUserIdAndFriendIdAndIsActiveTrue(User userId, User friendId);

    // List<Friend> findByStatusIdAndFriendIdOrUserId(int statusId, User user);
    @Query(value="select * from friendlist where friendlist_id in (select friendlist_id from friendlist where (request_sent_by = :user and status_id = :statusId) or "
    +"(request_sent_to = :user and status_id = :statusId))",nativeQuery = true)
    List<Friend> findByUserIdAndStatusId(@Param("user")Long userId,@Param("statusId")int statusId);

    // pending req
    List<Friend> findByFriendIdAndStatusIdAndIsActiveTrue(User user, int i);

}
