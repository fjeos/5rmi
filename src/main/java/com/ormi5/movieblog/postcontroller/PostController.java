package com.ormi5.movieblog.postcontroller;

import com.ormi5.movieblog.post.PostDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.List;

@RestController
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
	 * @author yuseok
	 * @param id 조회할 게시글의 ID
	 * @return 조회된 게시글 정보, 없다면 Optional.empty
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Optional<PostDto>> getPostById(@PathVariable("id") Long id) {
		System.out.println(postService.getPostById(id));
		return ResponseEntity.ok(postService.getPostById(id));
	}

	/**
	 * 특정 유저의 게시글 조회: 유저의 ID를 받아 게시글 조회 (검색)
	 *
	 * @author yuseok
	 * @param userId 원하는 유저의 User ID
	 * @return 조회된 게시글 정보, 없다면 빈 리스트 []
	 */
	@GetMapping("/user/{userId}")
	public ResponseEntity<List<PostDto>> getPostByUserId(@PathVariable Long userId) {
		List<PostDto> postDtoList = postService.getPostsByUserId(userId);

		return ResponseEntity.ok(postDtoList);
	}

	/**
	 * 제목과 일치하는 게시글 찾기
	 *
	 * @author yuseok
	 * @param title 검색할 제목
	 * @return 검색어와 일치하는 게시물 DTO 목록, 없으면 빈 리스트 []
	 */
	@GetMapping("/title")
	public ResponseEntity<List<PostDto>> getPostByTitle(@RequestParam String title) {
		List<PostDto> postDtoList = postService.getPostsByTitle(title);

		return ResponseEntity.ok(postDtoList);
	}

	/**
	 * 제목에 키워드를 포함하는 게시글을 대소문자를 구분하여 찾기
	 *
	 * @author yuseok
	 * @param keyword 검색할 키워드
	 * @return 제목에 키워드를 포함하는 게시물 DTO 목록, 없으면 빈 리스트 []
	 */
	@GetMapping("/title/containing/case-sensitive") // case sensitive: 대소문자 구분
	public ResponseEntity<List<PostDto>> getPostByContainingTitleCaseSensitive(@RequestParam String keyword) {
		/*
		현재 데이터베이스의 정렬 설정이 'utf8mb4_0900_ai_ci'
		즉, 대소문자를 구분하지 않는(ci) 상태이기에 대소문자 구분없이 검색되는 상태 (대소문자 구분: cs)
		DB에서 설정을 변경하기는 위험해서 PostService에서 contains()를 사용해 대소문자 구분
		*/
		List<PostDto> postDtoList = postService.getPostsByContainingTitleCaseSensitive(keyword);

		return ResponseEntity.ok(postDtoList);
	}

	/**
	 * 제목에 키워드를 포함하는 게시글을 대소문자 구분없이 찾기
	 *
	 * @author yuseok
	 * @param keyword 검색할 키워드
	 * @return 제목에 키워드를 포함하는 게시물 DTO 리스트, 없으면 빈 리스트 []
	 */
	@GetMapping("/title/containing/case-insensitive")
	public ResponseEntity<List<PostDto>> getPostByContainingTitleCaseInsensitive(@RequestParam String keyword) {
		List<PostDto> postDtoList = postService.getPostsByContainingTitleCaseInsensitive(keyword);

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