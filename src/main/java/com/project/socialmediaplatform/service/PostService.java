package com.project.socialmediaplatform.service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.socialmediaplatform.model.Post;
import com.project.socialmediaplatform.model.User;
import com.project.socialmediaplatform.repository.PostRepo;
import com.project.socialmediaplatform.repository.UserRepo;

@Service
public class PostService {

    @Autowired
    public PostRepo postRepo;

    @Autowired
    public UserRepo userRepo;

    public Post createPost(User user, Post newPost) {
        Post post = new Post();
        post.setUser(user);
        post.setContent(newPost.getContent());
        post.setCaption(newPost.getCaption());
        post.setDeleted(false);
        post.setPostedOn(Timestamp.from(Instant.now()));
        post.setLastModifiedOn(Timestamp.from(Instant.now()));
        return postRepo.save(post);
    }

    public Post editPost(Long postId, String updatedCaption) {
        Post existingPost = postRepo.findById(postId).get();
        existingPost.setCaption(updatedCaption);
        existingPost.setLastModifiedOn(Timestamp.from(Instant.now()));
        return postRepo.save(existingPost);
    }

    public void deletePost(Long postId) {
        postRepo.deleteById(postId);
    }

    public Post getPostById(Long postId) {
        return postRepo.findByPostId(postId).get(0);
    }
    

}
