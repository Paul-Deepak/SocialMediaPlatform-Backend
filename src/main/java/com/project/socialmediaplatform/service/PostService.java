package com.project.socialmediaplatform.service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.socialmediaplatform.Exception.PostNotFoundException;
import com.project.socialmediaplatform.model.Friend;
import com.project.socialmediaplatform.model.Post;
import com.project.socialmediaplatform.model.SearchModel;
import com.project.socialmediaplatform.model.User;
import com.project.socialmediaplatform.repository.PostRepo;
import com.project.socialmediaplatform.repository.UserRepo;

@Service
public class PostService {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private FriendListService friendListService;

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
        Post existingPost = postRepo.findByPostId(postId);
        if (existingPost == null) {
            throw new PostNotFoundException("No such Post Exist");
        }
        existingPost.setCaption(updatedCaption);
        existingPost.setLastModifiedOn(Timestamp.from(Instant.now()));
        return postRepo.save(existingPost);
    }

    public List<Post> searchPost(SearchModel searchModel) {
        List<Post> searchedPost = postRepo.findByCaption(searchModel.getSearchWord());
        return searchedPost;
    }

    public List<Post> searchPostWithFilter(Long userId, Long friendId, String searchWord) {
        try {
            List<Post> searchedPost = postRepo.findByCaptionAndFriendId(userId, friendId, searchWord);
            return searchedPost;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public void deletePost(Long postId) {
        Post post = postRepo.findByPostId(postId);
        if (post == null) {
            throw new PostNotFoundException("No Such Post Exists");
        }
        post.setLastModifiedOn(Timestamp.from(Instant.now()));
        post.setDeleted(true);
        postRepo.save(post);
    }

    public Post getPostById(Long postId) {
        return postRepo.findByPostId(postId);
    }

}
