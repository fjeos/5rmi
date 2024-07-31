package com.ormi5.movieblog.postcontroller;

import com.ormi5.movieblog.post.PostDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.List;

@Controller
@RequestMapping("/posts")
public class PostController {
	private final PostService postService;

	@Autowired
	public PostController(PostService postService) {
		this.postService = postService;
	}

	/**
	 * 게시글 생성 메서드
	 *
	 * @author yuseok
	 * @param postDto 생성할 게시글 정보가 담긴 DTO
	 * @return 생성된 게시글 정보가 담긴 DTO
	 */
	@PostMapping
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {
		PostDto createdPost = postService.createPost(postDto);

		return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
	}

	// 모든 게시글을 조회하는 메서드
	@GetMapping("/list")
	public ResponseEntity<List<PostDto>> getAllPosts() {
		List<PostDto> postDtos = postService.getAllPosts();
		return ResponseEntity.ok(postDtos);
	}

	/**
	 * 특정 게시글 조회: 게시글의 ID를 받아 해당 게시글 조회
	 *
	 * @author yuseok, nayoung
	 * @param postId 조회할 게시글의 ID
	 * @return String, post/detail 링크로 이동
	 */
	@GetMapping("/{id}")
	public String getPostById(@PathVariable("id") Long postId, Model model) {
		PostDto post = postService.getPostById(postId);
		model.addAttribute("post", post);

		return "post/detail";
	}

	/**
	 * 특정 유저의 게시글 조회: 유저의 ID를 받아 게시글 조회
	 *
	 * @author yuseok
	 * @param userId 원하는 유저의 User ID
	 * @return 조회된 게시글 정보, 없다면 빈 리스트 []
	 */
	@GetMapping("/user/{userId}")
	public ResponseEntity<List<PostDto>> getPostByUserId(@PathVariable Long userId) {
		System.out.println(postService.getPostsByUserId(userId));
		List<PostDto> postDtoList = postService.getPostsByUserId(userId);

		return ResponseEntity.ok(postDtoList);
	}

	@PutMapping("/{postId}")
	public ResponseEntity<?> updatePost(@PathVariable Long postId, @RequestBody PostDto postDto) {
		return ResponseEntity.ok(postService.updatePost(postId, postDto));
	}

	/**
	 * Delete post using given id.
	 *
	 * @author junhyun
	 * @param id - post id to delete
	 * @since 0.0.1
	 * @return result ResponseEntity
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Optional<PostDto>> deletePostById(@PathVariable("id") Long id) {
		boolean deleted = postService.deletePost(id);
		return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
	}
}