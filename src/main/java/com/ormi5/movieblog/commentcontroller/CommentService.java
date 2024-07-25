package com.ormi5.movieblog.commentcontroller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
	private final PostRepository postRepository;

	@Autowired
	public CommentService(CommentRepository commentRepository, PostRepository postRepository) {
		this.commentRepository = commentRepository;
		this.postRepository = postRepository;
	}

	public CommentDto createComment(CommentDto commentDto) {
		Long postId = commentDto.getPostId();

		Post post = postRepository.findById(postId)
			.orElseThrow(() -> new IllegalArgumentException("해당하는 게시글이 없습니다."));

		Comment comment = CommentDto.toEntity(commentDto, post);

		Comment savedComment = commentRepository.save(comment);

		return CommentDto.toDto(savedComment);
	}

	public List<CommentDto> getAllComments() {
		List<Comment> comments = commentRepository.findAll();

		return comments.stream()
			.map(CommentDto::toDto)
			.collect(Collectors.toList());
	}

	public List<CommentDto> getCommentsByPostId(Long postId) {
		Post post = postRepository.findById(postId)
			.orElseThrow(() -> new IllegalArgumentException("해당하는 게시글이 없습니다."));

		List<Comment> comments = commentRepository.findByPost(post);

		return comments.stream()
			.map(CommentDto::toDto)
			.collect(Collectors.toList());
	}

	/**
	 * 댓글 수정
	 * @param commentDto 수정할 내용이 담겨있는 dto
	 * @author nayoung
	 */
	@Transactional
	public CommentDto updateComment(CommentDto commentDto) {
		Comment comment = commentRepository.findById(commentDto.getId())
			.orElseThrow(() -> new IllegalArgumentException("Invalid comment ID"));
		comment.updateComment(commentDto);
		return CommentDto.toDto(comment);
	}
}