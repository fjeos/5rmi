package com.ormi5.movieblog.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ormi5.movieblog.entity.Post;
import com.ormi5.movieblog.service.PostService;
import com.ormi5.movieblog.dto.PostDto;

@RestController
@RequestMapping("/api/post")
public class PostController {
	// final
	private final PostService postService;

	@Autowired
	public  PostController(PostService postService) {
		this.postService = postService;
	}

	/* --------------- 생성 --------------- */
	@PostMapping
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {
		PostDto createdPost = postService.createPost(postDto);

		return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
	}


	/*// GET /posts/add 요청 처리: 새 게시물 작성 폼 표시
	@GetMapping("/add")
	public String addPost(Model model) {
		model.addAttribute("post", new Post());

		return "post/add";
	}

	@PostMapping
	public String addPost(@ModelAttribute("post") Post post) {
		// 빌더 패턴을 사용하여 Post 객체 생성 후 필드(id, createdAt) 설정
		post = Post.builder()
			.postId(post.getPostId()) // 연산자 앞에서 개행
			.author(post.getAuthor())
			.title(post.getTitle())
			.content(post.getContent())
			.isShared(post.getIsShared())
			.createdAt(post.getCreatedAt())
			.build();

		posts.add(post);

		return "redirect:/post";
	}*/
}