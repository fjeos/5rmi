package com.ormi5.movieblog.postcontroller;

import com.ormi5.movieblog.post.PostDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class PostController {
    private PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<PostDto>> getPostById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(postService.getPostById(id));
    }

    /**
     * Delete post using given id.
     *
     * @author junhyun
     * @param id - post id to delete
     * @since      0.0.1
     * @return result ResponseEntity
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Optional<PostDto>> getPostById(@PathVariable("id") int id) {
        boolean deleted = postService.deletePost(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
  
  	@PostMapping // YS: 어노테이션 꼭 붙일 것
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {
      PostDto createdPost = postService.createPost(postDto);

      return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }
}
