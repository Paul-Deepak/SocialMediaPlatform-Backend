package com.project.socialmediaplatform.repository;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.socialmediaplatform.model.Post;
import com.project.socialmediaplatform.model.User;

import java.util.List;

@Repository
public interface PostRepo extends JpaRepository<Post, Long>, JpaSpecificationExecutor<Post> {

        @Query("select p from Post p where p.postId = :postId AND p.isDeleted = false")
        Post findByPostId(Long postId);

        @Query("select p from Post p where p.user = :user AND p.isDeleted = false")
        List<Post> findByUser(User user);

        @Query(value = "SELECT p from Post p where "
                        + "LOWER(p.caption) LIKE LOWER(CONCAT('%', :searchWord, '%'))")
        List<Post> findByCaption(@Param("searchWord") String searchWord);

        @Query(value = "SELECT * from Posts where post_id IN(SELECT post_id from Posts as p LEFT JOIN Users u ON u.user_id = p.user_id "
                        + "LEFT JOIN Friendlist f ON u.user_id = f.request_sent_by "
                        + "WHERE ((f.request_sent_by = :userId AND f.status_id = '1' ) "
                        + "OR (f.request_sent_to = :userId AND f.status_id = '1' )) "
                        + "AND p.post_caption LIKE LOWER(CONCAT('%', :searchWord, '%')) AND p.is_deleted = false)", nativeQuery = true)
        List<Post> findByCaptionAndFriendList(@Param("userId") Long userId, @Param("searchWord") String searchWord);

        @Query(value = "SELECT * from Posts where post_id IN(SELECT post_id from Posts as p LEFT JOIN Users u ON u.user_id = p.user_id "
                        + "LEFT JOIN Friendlist f ON u.user_id = f.request_sent_by "
                        + "WHERE ((f.request_sent_by = :userId AND f.request_sent_to = :friendId AND f.status_id = '1' ) "
                        + "OR (f.request_sent_to = :friendId AND f.request_sent_by = :userId AND f.status_id = '1' )) "
                        + "AND p.post_caption LIKE LOWER(CONCAT('%', :searchWord, '%')) AND p.is_deleted = false)", nativeQuery = true)
        List<Post> findByCaptionAndFriendId(@Param("userId") Long userId, @Param("friendId") Long friendId,
                        @Param("searchWord") String searchWord);

        List<Post> findAll(Specification specs);

        // @Query("select p from post p where p.postId = :postId and p.isDeleted = 0")
        // List<Post> findByPostIdAndisDeleted(@Param("postId") Long postId);
}
