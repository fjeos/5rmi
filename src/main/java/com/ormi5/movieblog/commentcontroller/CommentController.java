package com.ormi5.movieblog.commentcontroller;

import com.ormi5.movieblog.comment.CommentDto;
import com.ormi5.movieblog.post.PostDto;
import com.ormi5.movieblog.postcontroller.PostService;
import com.ormi5.movieblog.user.User;
import com.ormi5.movieblog.user.UserDto;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import com.ormi5.movieblog.usercontroller.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/comments")
public class CommentController {
	private final CommentService commentService;
	private final UserService userService;
	private final PostService postService;

	@Autowired
	public CommentController(CommentService commentService, PostService postService, UserService userService) {
		this.commentService = commentService;
		this.postService = postService;
		this.userService = userService;
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
	public ResponseEntity<?> deleteComment(@RequestBody Map<String, Integer> request) {
		try {
			Integer commentId = request.get("commentId");
			Integer userId = request.get("userId");

			if (commentId == null || userId == null) {
				return ResponseEntity.badRequest().body("'commentId'와 'userId' 모두 필요합니다");
			}

			commentService.deleteComment(commentId, userId);
			return ResponseEntity.ok().build();
		} catch (RuntimeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	/**
	 * RequestBody에서 JSON 형식으로 postId와 commentId를 받아 옴
	 * @param likeRequest postId와 commentId
	 * @return 기존 post 상세 조회 화면
	 */
	@PostMapping("/like")
	public ResponseEntity<CommentDto> commentLike(@RequestBody Map<String, Integer> likeRequest, Model model) {
		Integer postId = likeRequest.get("postId");
		Integer commentId = likeRequest.get("commentId");
		commentService.increaseLike(postId, commentId);
		return new ResponseEntity<>(commentService.getCommentById(commentId), HttpStatus.OK);
	}

	@PostMapping("/dislike")
	public ResponseEntity<CommentDto> commentDislike(@RequestBody Map<String, Integer> likeRequest, Model model) {
		Integer postId = likeRequest.get("postId");
		Integer commentId = likeRequest.get("commentId");
		commentService.decreaseLike(postId, commentId);
		return new ResponseEntity<>(commentService.getCommentById(commentId), HttpStatus.OK);
	}

	@GetMapping("/{postId}/add")
	public String addCommentForm(@PathVariable("postId") Integer postId, Model model) {
		model.addAttribute("postId", postId);

		return "/comment/add_comment";
	}

	@PostMapping("/{postId}/add")
	public String addComment(@PathVariable("postId") Integer postId, Principal principal,
		@RequestParam("content") String content) {
		PostDto postDto = postService.getPostById(postId);

		User user = userService.findByUsername(principal.getName());

		CommentDto commentDto = CommentDto.builder()
			.post(postDto)
			.user(UserDto.fromEntity(user))
			.content(content)
			.build();

		commentService.addComment(commentDto);

		// 댓글을 생성 후 게시글 상세보기 페이지로 리다이렉트
		return "redirect:/posts/" + postDto.getPostId();
	}

	@GetMapping("/{commentId}/edit")
	public String editCommentForm(@PathVariable("commentId") Integer commentId,
		@RequestParam Long postId, Model model, Principal principal) {
		CommentDto commentDto = commentService.getCommentById(commentId);

		User user = userService.findByUsername(principal.getName());

		if (!commentDto.getUser().getId().equals(user.getId())) {
			System.out.println("수정 권한이 없습니다");
			return "redirect:/posts/" + postId;
		}

		model.addAttribute("comment", commentDto);
		model.addAttribute("postId", postId);

		return "comment/edit_comment";
	}

	@PostMapping("/{commentId}/edit")
	public String editComment(@PathVariable("commentId") Integer commentId,
		@RequestParam Integer postId, @RequestParam String content) {
		CommentDto commentDto = commentService.getCommentById(commentId);

		CommentDto updatedComment = CommentDto.builder()
			.commentId(commentDto.getCommentId())
			.post(commentDto.getPost())
			.user(commentDto.getUser())
			.content(content)
			.likes(commentDto.getLikes())
			.dislikes(commentDto.getDislikes())
			.createAt(commentDto.getCreateAt())
			.build();

		commentService.editComment(commentId, updatedComment);

		return "redirect:/posts/" + postId;
	}

	@PostMapping("/{commentId}/delete")
	public String deleteComment(@PathVariable("commentId") Integer commentId,
		@RequestParam Integer userId,
		@RequestParam Integer postId, Principal principal) {

		CommentDto commentDto = commentService.getCommentById(commentId);

		User user = userService.findByUsername(principal.getName());

		if (!commentDto.getUser().getId().equals(user.getId())) {
			System.out.println("삭제 권한이 없습니다");
			return "redirect:/posts/" + postId;
		}

		commentService.deleteComment(commentId, userId);

		return "redirect:/posts/" + postId;
	}
}