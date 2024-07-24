package com.ormi5.movieblog.postcontroller;

import com.ormi5.movieblog.post.PostDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/board-posts")
public class PostController {

    private final PostService boardPostService;

    @Autowired
    public PostController(PostService boardPostService) {
        this.boardPostService = boardPostService;
    }

    @PostMapping
    public ResponseEntity<PostDto> createBoardPost(@RequestBody PostDto postDto) {
        PostDto createdPostDto = boardPostService.createBoardPost(postDto);
        return ResponseEntity.ok(createdPostDto);
    }

    @GetMapping
    public ResponseEntity<List<PostDto>> getAllBoardPosts() {
        List<PostDto> postDtos = boardPostService.getAllBoardPosts();
        return ResponseEntity.ok(postDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getBoardPostById(@PathVariable("id") Long id) {
        PostDto postDto = boardPostService.getBoardPostById(id);
        return ResponseEntity.ok(postDto);
    }
}