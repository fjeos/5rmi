package com.ormi5.movieblog.commentcontroller;

import com.ormi5.movieblog.comment.Comment;
import com.ormi5.movieblog.comment.CommentDto;
import com.ormi5.movieblog.post.PostDto;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
public class CommentController {
	private final CommentService commentService;
	private final CommentRepository commentRepository;

	@Autowired
	public CommentController(CommentService commentService, CommentRepository commentRepository) {
		this.commentService = commentService;
		this.commentRepository = commentRepository;
	}

	@PostMapping
	public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto) {
		CommentDto createComment = commentService.createComment(commentDto);

		return new ResponseEntity<>(createComment, HttpStatus.CREATED);
	}

	@GetMapping("/list")
	public ResponseEntity<List<CommentDto>> getAllComments() {
		List<CommentDto> commentDtoList = commentService.getAllComments();

		return ResponseEntity.ok(commentDtoList);
	}

	@GetMapping
	public ResponseEntity<List<CommentDto>> getCommentByPostId(@RequestBody PostDto postDto) {
		List<CommentDto> commentDtoList = commentService.getCommentsByPostId(postDto);

		return ResponseEntity.ok(commentDtoList);
	}

	/**
	 * 댓글 수정 controller
	 * @param commentDto 수정할 내용이 담겨있는 dto
	 * @return 수정한 내용을 반영한 dto
	 * @author nayoung
	 */
	@PutMapping
	public ResponseEntity<CommentDto> updateComment(@RequestBody CommentDto commentDto) {
		return ResponseEntity.ok(commentService.updateComment(commentDto));
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