package com.ormi5.movieblog.commentcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ormi5.movieblog.comment.Comment;
import com.ormi5.movieblog.comment.CommentDto;
import com.ormi5.movieblog.post.Post;
import com.ormi5.movieblog.postcontroller.PostRepository;

@Service
public class CommentService {
	private final CommentRepository commentRepository;
	private final PostRepository postRepository; // PostRepository를 추가하여 Post를 참조합니다.

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

	/**
	 * 댓글 수정
	 * @param commentDto 수정할 내용이 담겨있는 dto
	 * @author nayoung
	 */
	@Transactional
	public CommentDto updateComment(CommentDto commentDto) {
		Comment comment = commentRepository.findById(commentDto.getId())
				.orElseThrow(() -> new IllegalArgumentException("Invalid comment ID"));// commentId를 검증합니다.
		comment.updateComment(commentDto);
		return CommentDto.toDto(comment);
	}
}