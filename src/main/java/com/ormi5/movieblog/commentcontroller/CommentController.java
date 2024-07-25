package com.ormi5.movieblog.commentcontroller;

import com.ormi5.movieblog.comment.Comment;
import com.ormi5.movieblog.comment.CommentDto;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/comment")
public class CommentController {
	private final CommentService commentService;
  private final CommentRepository commentRepository;
	
  public CommentController(CommentService commentService) {
		this.commentService = commentService;
	}
 
  @Autowired
  public CommentController(CommentRepository commentRepository) {
      this.commentRepository = commentRepository;
  }

	@PostMapping
	public ResponseEntity<CommentDto> addComment(@RequestBody CommentDto commentDto) {
		CommentDto createComment = commentService.createComment(commentDto);

		return new ResponseEntity<>(createComment, HttpStatus.CREATED);
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