package com.ormi5.movieblog.commentcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ormi5.movieblog.comment.Comment;
import com.ormi5.movieblog.comment.CommentDto;
import com.ormi5.movieblog.post.Post;
import com.ormi5.movieblog.postcontroller.PostRepository;

@Service
public class CommentService {
	private final CommentRepository commentRepository;
	private final PostRepository postRepository; // PostRepository를 추가하여 Post를 참조합니다.

	/*@Autowired
	public CommentService(CommentRepository commentRepository) {
		this.commentRepository = commentRepository;
	}*/

	/*public CommentDto createComment(CommentDto commentDto) {
		Comment comment = CommentDto.toEntity(commentDto);

		Comment saveComment = commentRepository.save(comment);

		return CommentDto.toDto(saveComment);
	}*/

	@Autowired
	public CommentService(CommentRepository commentRepository, PostRepository postRepository) {
		this.commentRepository = commentRepository;
		this.postRepository = postRepository;
	}

	@Transactional // 트랜잭션 관리를 위한 어노테이션을 추가합니다.
	public CommentDto createComment(CommentDto commentDto) {
		Post post = postRepository.findById(commentDto.getPostId())
			.orElseThrow(() -> new IllegalArgumentException("Invalid post ID")); // postId를 검증합니다.

		Comment comment = CommentDto.toEntity(commentDto, post);

		Comment savedComment = commentRepository.save(comment);

		return CommentDto.toDto(savedComment);
	}
}