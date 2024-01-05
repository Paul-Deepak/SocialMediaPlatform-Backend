package com.project.socialmediaplatform.service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.ProviderNotFoundException;
import org.springframework.stereotype.Service;

import com.project.socialmediaplatform.controller.PostModel;
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

    // public Post createPost(PostModel postModel) {

    // try {

    // Post post = new Post();

    // User user = userRepo.getReferenceById(postModel.getUserId());

    // post.setUser(user);
    // post.setContent(postModel.getContent().getBytes());
    // post.setPostedOn(Timestamp.from(Instant.now()));
    // post.setLastModifiedOn(Timestamp.from(Instant.now()));
    // postRepo.save(post);
    // return post;
    // } catch (Exception e) {
    // e.printStackTrace();
    // }
    // return null;
    // }


    public Post createPost(User user, byte[] postContent) {
        Post post = new Post();
        post.setUser(user);
        post.setContent(postContent);
        post.setPostedOn(Timestamp.from(Instant.now()));
        post.setLastModifiedOn(Timestamp.from(Instant.now()));
        return postRepo.save(post);
    }

    public Post editPost(Long postId, byte[] updatedContent) {
        Post existingPost = postRepo.findById(postId)
                .orElseThrow(() -> new ProviderNotFoundException("Post not found with ID: " + postId));
        existingPost.setContent(updatedContent);
        existingPost.setLastModifiedOn(Timestamp.from(Instant.now()));
        return postRepo.save(existingPost);
    }

    public void deletePost(Long postId) {
        try {
            postRepo.deleteById(postId);
        } catch (Exception E) {
            E.printStackTrace();
        }
    }

    public Post getPostById(Long postId) {
        return null;
    }

}
