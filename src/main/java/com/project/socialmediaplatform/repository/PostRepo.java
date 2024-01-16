package com.project.socialmediaplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.socialmediaplatform.model.Post;
import com.project.socialmediaplatform.model.SearchModel;
import com.project.socialmediaplatform.model.User;

import java.util.List;

@Repository
public interface PostRepo extends JpaRepository<Post,Long>{


    @Query("select p from Post p where p.postId = :postId AND p.isDeleted = false")
    List<Post> findByPostId(Long postId);

    @Query("select p from Post p where p.user = :user AND p.isDeleted = false")
    List<Post> findByUser(User user);


    // @Query("select p from post p where p.caption like '%caption%'")
    // List<Post> findByCaption(String caption);
    
    // @Query("select p from Post p inner join Friendlist f on p.userId = f.userId "
    // + "where lower(p.caption) like lower(concat('%', :searchedWord, '%'))")

    // @Query("SELECT p FROM Post p JOIN p.User u JOIN u.Friend f.friendId " 
    // + "WHERE (f.userId = :#{#searchModel.userId} AND f.friendId = :#{#searchModel.friendId} AND f.statusId = '1' ) "
    // // + "OR (f.friendId = :#{#searchModel.userId} AND f.userId = :#{#searchModel.friendId} AND f.statusId = '1' ) "
    // + "AND LOWER(p.content) LIKE LOWER(CONCAT('%', :#{#searchModel.searchWord}, '%')) ")
    
    @Query(value="SELECT P from Post p INNER JOIN User u ON u.user_id = p.user_id "
    + "INNER JOIN Friend f ON u.user_id = f.req_sent_by " 
    + "WHERE (f.req_sent_by = :#{#searchModel.userId} AND f.req_sent_to = :#{#searchModel.friendId} AND f.statusId = '1' ) "
    + "OR (f.req_sent_to = :#{#searchModel.userId} AND f.req_sent_by = :#{#searchModel.friendId} AND f.statusId = '1' ) "
    + "AND LOWER(p.caption) LIKE LOWER(CONCAT('%', :#{#searchModel.searchWord}, '%')) )", nativeQuery= true)
    List<Post> findByCaption(@Param("searchModel") SearchModel searchModel);


    // @Query("select p from post p where p.postId = :postId and p.isDeleted = 0")
    // List<Post> findByPostIdAndisDeleted(@Param("postId") Long postId);
}
