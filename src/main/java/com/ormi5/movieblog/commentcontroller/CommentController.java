package com.ormi5.movieblog.commentcontroller;

import com.ormi5.movieblog.comment.Comment;
import com.ormi5.movieblog.comment.CommentDto;
import com.ormi5.movieblog.post.PostDto;

import java.util.List;

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

	/*@GetMapping("/{postId}")
	public ResponseEntity<List<CommentDto>> getCommentByPostId(@PathVariable Long postId) {
		List<CommentDto> commentDtoList = commentService.getCommentsByPostId(postId);

		return ResponseEntity.ok(commentDtoList);
	}*/
	
	// URL 없이 조회
	@GetMapping
	public ResponseEntity<List<CommentDto>> getCommentByPostId(@RequestBody PostDto postDto) {
		List<CommentDto> commentDtoList = commentService.getCommentsByPostId(postDto);

		return ResponseEntity.ok(commentDtoList);
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
}