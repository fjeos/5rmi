package com.ormi5.movieblog.commentcontroller;

import com.ormi5.movieblog.comment.Comment;
import com.ormi5.movieblog.comment.CommentDto;
import com.ormi5.movieblog.post.Post;
import com.ormi5.movieblog.post.PostDto;
import com.ormi5.movieblog.postcontroller.PostRepository;

import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@RequiredArgsConstructor
public class CommentService {
	private final CommentRepository commentRepository;
	private final PostRepository postRepository;

	@Transactional
	public CommentDto createComment(CommentDto commentDto) {
		Post post = postRepository.findById(commentDto.getPost().getPostId())
			.orElseThrow(() -> new IllegalArgumentException("Invalid post ID"));

		Comment comment = commentDto.toEntity();

		Comment savedComment = commentRepository.save(comment);

		return CommentDto.toDto(savedComment);
	}

	@Transactional
	public void addComment(CommentDto commentDto) {
		Post post = postRepository.findById(commentDto.getPost().getPostId())
			.orElseThrow(() -> new IllegalArgumentException("해당하는 게시글이 없습니다."));

		Comment comment = commentDto.toEntity();

		commentRepository.save(comment);
	}

	@Transactional
	public List<CommentDto> getAllComments() {
		List<Comment> comments = commentRepository.findAll();

		return comments.stream()
			.map(CommentDto::toDto)
			.collect(Collectors.toList());
	}

	@Transactional
	public List<CommentDto> getCommentsByPostId(PostDto postDto) {
		Post post = postRepository.findById(postDto.getPostId())
			.orElseThrow(() -> new IllegalArgumentException("해당하는 게시글이 없습니다."));

		List<Comment> comments = commentRepository.findByPost(post);

		return comments.stream()
			.map(CommentDto::toDto)
			.collect(Collectors.toList());
	}

	@Transactional
	public CommentDto getCommentById(Integer commentId) {
		Comment comment = commentRepository.findById(commentId)
			.orElseThrow(() -> new IllegalArgumentException("해당하는 댓글이 없습니다."));

		return CommentDto.toDto(comment);
	}

	/**
	 * 댓글 수정
	 * @param commentDto 수정할 내용이 담겨있는 dto
	 * @author nayoung
	 */
	@Transactional
	public CommentDto updateComment(CommentDto commentDto) {
		Comment comment = commentRepository.findById(commentDto.getCommentId())
				.orElseThrow(() -> new IllegalArgumentException("해당하는 댓글이 없습니다."));
		comment.updateComment(commentDto);
		return CommentDto.toDto(comment);
	}

	@Transactional
	public void editComment(Integer commentId, CommentDto commentDto) {
		Comment comment = commentRepository.findById(commentId)
			.orElseThrow(() -> new IllegalArgumentException("해당하는 댓글이 없습니다."));

		comment.updateComment(commentDto);
	}

	@Transactional
	public void deleteComment(Integer commentId, Integer userId) {
		Comment comment = commentRepository.findById(commentId)
			.orElseThrow(() -> new RuntimeException("댓글을 찾을 수 없습니다"));

		if (!comment.getUser().getId().equals(userId)) {
			throw new RuntimeException("댓글을 삭제할 권한이 없습니다");
		}

		commentRepository.delete(comment);
	}

	@Transactional
    public void increaseLike(Integer postId, Integer commentId) {
		Comment findComment = commentRepository.findByPost_PostIdAndId(postId, commentId);
		findComment.increaseLike();
	}

	@Transactional
	public void decreaseLike(Integer postId, Integer commentId) {
		Comment findComment = commentRepository.findByPost_PostIdAndId(postId, commentId);
		findComment.decreaseLike();
	}
}