package com.ormi5.movieblog.postcontroller;

import com.ormi5.movieblog.post.PostDto;
import com.ormi5.movieblog.post.PostResponseDto;

import com.ormi5.movieblog.comment.CommentDto;
import com.ormi5.movieblog.commentcontroller.CommentService;
import com.ormi5.movieblog.post.Post;
import com.ormi5.movieblog.post.PostDto;

import com.ormi5.movieblog.post.PostUpdateDto;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;
import java.util.List;

@Controller
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {
	private final PostService postService;

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
	public String getPostById(@PathVariable("id") Integer postId, Model model) {
		PostDto post = postService.getPostById(postId);
		model.addAttribute("post", post);

		return "post/detail";
	}

	/**
	 * 특정 게시글 조회: 게시글의 ID를 받아 해당 게시글 조회
	 *
	 * @author junhyun
	 * @param keyword 조회할 게시글 제목 keyword
	 * @return 조회된 게시글 정보, 없다면 Optional.empty
	 */
	@GetMapping()
	public ResponseEntity<List<PostDto>> getPostByKeyword(@RequestParam("keyword") String keyword) {

		return ResponseEntity.ok(postService.getPostByKeyword(keyword));
	}

	/**
	 * 특정 유저의 게시글 조회: 유저의 ID를 받아 게시글 조회 (검색)
	 *
	 * @author yuseok
	 * @param userId 원하는 유저의 User ID
	 * @return 조회된 게시글 정보, 없다면 빈 리스트 []
	 */
	@GetMapping("/user/{userId}")
	public ResponseEntity<List<PostDto>> getPostByUserId(@PathVariable Integer userId) {
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

	/**
	 * post 수정 form을 불러오는 메서드
	 * @param postId 수정할 게시글 Id
	 * @param model 게시글 정보를 담은 model
	 * @return 수정 form
	 * @author nayoung
	 */
	@GetMapping("/{postId}/edit")
	public String editForm(@PathVariable("postId") Integer postId, Model model) {
		PostDto post = postService.getPostById(postId);
		model.addAttribute("post", post);
		return "post/edit";
	}

	/**
	 * 게시글 수정 요청이 들어왔을 때 실행되는 메서드
	 * @param postId 수정할 게시글 Id
	 * @param updatePost 수정 정보가 담긴 Dto
	 * @return 기존 게시글 상세보기 페이지
	 * @author nayoung
	 */
	@PostMapping("/{postId}/edit")
	public String edit(@PathVariable("postId") Integer postId, @ModelAttribute PostResponseDto updatePost) {
		postService.updatePost(postId, updatePost);
		return "redirect:/board";
	}

	/**
	 * 게시글의 좋아요를 증가시키는 컨트롤러
	 * @param postId 게시글 Id
	 * @param model 게시글의 나머지 정보
	 * @return 기존 게시글 화면으로 redirect
	 * @author nayoung
	 */
	@PostMapping("/{postId}/like")
	public String increaseLike(@PathVariable("postId") Long postId, Model model) {
		postService.increaseLike(postId, model);
		model.addAttribute(model);
		return "redirect:/posts/{postId}";
	}

	/**
	 * Delete post using given id.
	 *
	 * @author junhyun
	 * @param id - post id to delete
	 * @since 0.0.1
	 * @return result ResponseEntity
	 */
	@PostMapping("/{id}/delete")
	public String deletePostById(@PathVariable("id") Integer id) {
		boolean deleted = postService.deletePost(id);
		return "redirect:/board";
	}

	@GetMapping("/recent")
	public ResponseEntity<List<PostDto>> getRecentPosts(@RequestParam User user, @RequestParam int limit) {
		List<PostDto> posts = postService.getRecentPosts(user, limit);
		return ResponseEntity.ok(posts);
	}

	@GetMapping("/all")
	public ResponseEntity<List<PostDto>> getAllPosts(@RequestParam User user) {
		List<PostDto> posts = postService.getAllPosts(user);
		return ResponseEntity.ok(posts);
	}
}