package com.ormi5.movieblog.commentcontroller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ormi5.movieblog.comment.CommentDto;

@RestController
@RequestMapping("/comments")
public class CommentController {
	private final CommentService commentService;

	public CommentController(CommentService commentService) {
		this.commentService = commentService;
	}

	@PostMapping
	public ResponseEntity<CommentDto> addComment(@RequestBody CommentDto commentDto) {
		CommentDto createComment = commentService.createComment(commentDto);

		return new ResponseEntity<>(createComment, HttpStatus.CREATED);
	}
}