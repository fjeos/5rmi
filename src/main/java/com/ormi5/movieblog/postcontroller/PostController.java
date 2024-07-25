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

	@PostMapping
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {
		PostDto createdPost = postService.createPost(postDto);

		return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<PostDto>> getAllPosts() {
		List<PostDto> postDtos = postService.getAllPosts();
		return ResponseEntity.ok(postDtos);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Optional<PostDto>> getPostById(@PathVariable("id") Long id) {
		return ResponseEntity.ok(postService.getPostById(id));
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