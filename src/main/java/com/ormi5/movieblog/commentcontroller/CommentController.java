package com.ormi5.movieblog.commentcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @DeleteMapping
    public ResponseEntity<?> deleteComment(@RequestBody Map<String, Long> request) {
        try {
            Long commentId = request.get("id");
            Long userId = request.get("userId");

            if (commentId == null || userId == null) {
                return ResponseEntity.badRequest().body("'id'와 'userId' 모두 필요합니다");
            }

            commentService.deleteComment(commentId, userId);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
