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
        post.setDeleted(false);
        post.setPostedOn(Timestamp.from(Instant.now()));
        post.setLastModifiedOn(Timestamp.from(Instant.now()));
        return postRepo.save(post);
    }

    public Post editPost(Long postId, byte[] updatedContent) {
        Post existingPost = postRepo.findById(postId).get();
        existingPost.setContent(updatedContent);
        existingPost.setLastModifiedOn(Timestamp.from(Instant.now()));
        return postRepo.save(existingPost);
    }

    public Post deletePost(Long postId) {
        try {
            Post deletedPost=postRepo.findByPostId(postId).get(0);
            deletedPost.setDeleted(true);
            return postRepo.save(deletedPost);
        } catch (Exception E) {
            E.printStackTrace();
        }
        return null;
    }

    public Post getPostById(Long postId) {
        return postRepo.findByPostId(postId).get(0);
    }
    

}
