package com.ormi5.movieblog.postcontroller;

import com.ormi5.movieblog.post.PostDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

	@PostMapping // YS: 어노테이션 꼭 붙일 것
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {
		PostDto createdPost = postService.createPost(postDto);

		return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<PostDto>> getAllBoardPosts() {
		List<PostDto> postDtos = postService.getAllBoardPosts();
		return ResponseEntity.ok(postDtos);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Optional<PostDto>> getBoardPostById(@PathVariable("id") int id) {
		return ResponseEntity.ok(postService.getPostById(id));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Optional<PostDto>> getPostById(@PathVariable("id") int id) {
		return ResponseEntity.ok(postService.getPostById(id));
	}

	@PutMapping("/{postId}")
	public ResponseEntity<?> updatePost(@PathVariable int postId, @RequestBody PostDto postDto) {
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
	public ResponseEntity<Optional<PostDto>> deletePostById(@PathVariable("id") int id) {
		boolean deleted = postService.deletePost(id);
		return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
	}

}