package com.ormi5.movieblog.commentcontroller;

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

	@Autowired
	public CommentController(CommentService commentService) {
		this.commentService = commentService;
	}

	/**
	 * 새로운 댓글 생성
	 *
	 * @author yuseok
	 * @param commentDto 댓글 DTO
	 * @return 생성된 댓글 DTO
	 */
	@PostMapping
	public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto) {
		CommentDto createComment = commentService.createComment(commentDto);

		return new ResponseEntity<>(createComment, HttpStatus.CREATED);
	}

	// 모든 댓글 조회
	@GetMapping("/list")
	public ResponseEntity<List<CommentDto>> getAllComments() {
		List<CommentDto> commentDtoList = commentService.getAllComments();

		return ResponseEntity.ok(commentDtoList);
	}

	/**
	 * 게시물 ID에 해당하는 댓글 리스트 가져오기
	 *
	 * @author yuseok
	 * @param postDto 게시물 DTO
	 * @return 댓글 DTO 리스트, 없으면 빈 리스트 []
	 */
	@GetMapping
	public ResponseEntity<List<CommentDto>> getCommentByPostId(@RequestBody PostDto postDto) {
		List<CommentDto> commentDtoList = commentService.getCommentsByPostId(postDto);
		System.out.println(commentDtoList);
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
			Long commentId = request.get("commentId");
			Long userId = request.get("userId");

			if (commentId == null || userId == null) {
				return ResponseEntity.badRequest().body("'commentId'와 'userId' 모두 필요합니다");
			}

			commentService.deleteComment(commentId, userId);
			return ResponseEntity.ok().build();
		} catch (RuntimeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}