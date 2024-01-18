package com.project.socialmediaplatform.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.socialmediaplatform.model.User;

@Repository
public interface UserRepo extends JpaRepository<User,Long>{

    @Query("select u from User u where u.email = :email AND u.isActive=true")
    User findByEmail(String email);

    @Query("select u from User u where u.userId = :userId AND u.isActive=true")
    User findByUserId(Long userId);

    @Query("select u from User u where u.userName LIKE LOWER(CONCAT('%', :searchWord , '%'))  AND u.isActive=true")
    List<User> findAllByUserName(String searchWord);
    
}
