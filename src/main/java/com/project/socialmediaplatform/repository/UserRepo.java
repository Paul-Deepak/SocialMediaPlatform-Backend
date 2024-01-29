package com.project.socialmediaplatform.repository;

import java.util.Date;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
// import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

import com.project.socialmediaplatform.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    @Query("select u from User u where u.email = :email AND u.isActive=true")
    User findByEmail(String email);

    @Query("select u from User u where u.email = :email")
    User findByEmailId(String email);

    @Query("select u from User u where u.userId = :userId AND u.isActive=true")
    User findByUserId(Long userId);

    @Query("select u from User u where u.userName LIKE LOWER(CONCAT('%', :searchWord , '%'))  AND u.isActive=true")
    List<User> findAllByUserName(String searchWord);

    // @Query(value = "INSERT IGNORE INTO users(user_name, email, password, "+
    // "profile_pic, bio, created_date, modified_date, is_active) VALUES (:userName, "+
    // ":email, :password, :profilePic, :bio, :createdDate, :modifiedDate, :isActive)", nativeQuery = true)
    // User insertUserIgnoreDuplicate(
    // @Param("userName") String userName,
    // @Param("email") String email,
    // @Param("password") String password,
    // @Param("profilePic") byte[] bs,
    // @Param("bio") String bio,
    // @Param("createdDate") Date date,
    // @Param("modifiedDate") Date date2,
    // @Param("isActive") boolean isActive
    // );
}
