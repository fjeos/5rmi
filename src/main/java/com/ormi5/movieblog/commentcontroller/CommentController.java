package com.ormi5.movieblog.commentcontroller;

import com.ormi5.movieblog.comment.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentRepository commentRepository;

    @Autowired
    public CommentController(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable Long id, @RequestBody DeleteCommentDto deleteCommentDto) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found"));

        if (!comment.getUserId().equals(deleteCommentDto.getUserId())) {
            return ResponseEntity.badRequest().body("User not authorized to delete this comment");
        }

        commentRepository.delete(comment);
        return ResponseEntity.ok().build();
    }
}