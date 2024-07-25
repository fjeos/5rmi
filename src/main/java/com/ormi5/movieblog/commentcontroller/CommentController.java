package com.ormi5.movieblog.commentcontroller;

import com.ormi5.movieblog.comment.Comment;
import com.ormi5.movieblog.comment.CommentDto;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

	@PostMapping
	public ResponseEntity<CommentDto> addComment(@RequestBody CommentDto commentDto) {
		CommentDto createComment = commentService.createComment(commentDto);

		return new ResponseEntity<>(createComment, HttpStatus.CREATED);
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