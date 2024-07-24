package com.ormi5.movieblog.postcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ormi5.movieblog.post.PostDto;

@RestController
@RequestMapping("/api/post")
public class PostController {
	private final PostService postService;

	@Autowired
	public PostController(PostService postService) {
		this.postService = postService;
	}

	@PostMapping // 어노테이션 꼭 붙일 것
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {
		PostDto createdPost = postService.createPost(postDto);

		return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
	}
}