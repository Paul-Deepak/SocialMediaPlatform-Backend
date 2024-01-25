package com.project.socialmediaplatform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.project.socialmediaplatform.Exception.ValidationException;
import com.project.socialmediaplatform.model.Comment;
import com.project.socialmediaplatform.model.User;
import com.project.socialmediaplatform.service.CommentService;

@RestController
@RequestMapping("/api/user/post")

public class CommentController extends UserManager{

    @Autowired
    private CommentService commentService;
  
     // addcomment
    @PostMapping("/{postId}/comment")
    public ResponseEntity<Comment> addComment(@PathVariable Long postId,@RequestBody Comment commentText) {
        User user = getUserFromAuthentication();
        if (commentText.getCommentText() == null || commentText.getCommentText().trim().isEmpty()) {
            throw new ValidationException("Comment field is empty");
        }
        Comment addedComment = commentService.addComment(postId, commentText.getCommentText(), user.getUserId());
        return ResponseEntity.ok(addedComment);
    }
    @PostMapping("/{postId}/comment/commentId")
    public ResponseEntity<Comment> addComment(@PathVariable Long postId,@RequestBody Comment commentText,@PathVariable Comment commentId) {
        User user = getUserFromAuthentication();
        if (commentText.getCommentText() == null || commentText.getCommentText().trim().isEmpty()) {
            throw new ValidationException("Comment field is empty");
        }
        Comment addedComment = commentService.addComment(postId, commentText.getCommentText(), user.getUserId(), commentId);
        return ResponseEntity.ok(addedComment);
    }

    @PutMapping("/comment/{commentId}")
    public ResponseEntity<Comment> editComment(@PathVariable Long commentId, @RequestBody Comment updatedComment) {
         User user = getUserFromAuthentication();
         
        if(updatedComment.getCommentText()==null || updatedComment.getCommentText().trim().isEmpty()){
            throw new ValidationException("Comment should not be empty");
        }
        Comment editedComment = commentService.editComment(user, commentId, updatedComment.getCommentText());
        return ResponseEntity.ok(editedComment);
    }

    @DeleteMapping("/comment/{commentId}")
    public ResponseEntity<Comment> removeComment(@PathVariable Long commentId) {
        User user = getUserFromAuthentication();
        Comment delComment=commentService.deleteComment(user,commentId);
        return ResponseEntity.ok(delComment);
    }
    
}
