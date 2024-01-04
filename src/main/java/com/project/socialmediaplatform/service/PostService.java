package com.project.socialmediaplatform.service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.socialmediaplatform.model.Post;
import com.project.socialmediaplatform.repository.PostRepo;

@Service
public class PostService {

@Autowired
private PostRepo postRepo;
    public Post createPost(Post post) {
        post.setPostedOn(Timestamp.from(Instant.now()));
        post.setLastModifiedOn(Timestamp.from(Instant.now()));
        postRepo.save(post);
        return null;
    }

    public List<Post> getAllPosts() {
        return postRepo.findAll();
    }

    public Post updatePost(Long postId, Post updatedPost) {
        return postRepo.save(updatedPost);
    }

    public void deletePost(Long postId) {
        postRepo.deleteById(postId);
    }
    
}
