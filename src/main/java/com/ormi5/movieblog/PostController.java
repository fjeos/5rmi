package com.ormi5.movieblog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    @Autowired
    public  PostController(PostService postService) {
        this.postService = postService;
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
}
