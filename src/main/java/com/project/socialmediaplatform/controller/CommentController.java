package com.project.socialmediaplatform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.socialmediaplatform.model.Comment;
import com.project.socialmediaplatform.service.CommentService;

@RestController
@RequestMapping("/api/user/post/comment")

public class CommentController {

    @Autowired
    private CommentService commentService;

    @PutMapping("/{commentId}")
    public ResponseEntity<Comment> editComment(
            @PathVariable Long commentId,
            @RequestBody String updatedCommentText) {
        Comment editedComment = commentService.editComment(commentId, updatedCommentText);
        return ResponseEntity.ok(editedComment);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Comment> removeComment(@PathVariable Long commentId) {
        Comment delComment=commentService.deleteComment(commentId);
        return ResponseEntity.ok(delComment);
    }

    
}
