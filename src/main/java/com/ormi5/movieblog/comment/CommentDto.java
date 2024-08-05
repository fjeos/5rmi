package com.ormi5.movieblog.comment;

import com.ormi5.movieblog.post.Post;

import lombok.*;

import java.time.Instant;

@Getter
@Builder
public class CommentDto {
	private Long commentId;
	private Long postId;
	private Long userId;
	private String content;
	private int likes;
	private int dislikes;
	private Instant createAt;
	private Instant updateAt;

	public static CommentDto toDto(Comment comment) {
		return CommentDto.builder()
			.commentId(comment.getCommentId())
			.postId(comment.getPost().getPostId())
			.userId(comment.getUserId())
			.content(comment.getContent())
			.likes(comment.getLikes())
			.dislikes(comment.getDislikes())
			.createAt(comment.getCreateAt())
			.updateAt(comment.getUpdateAt())
			.build();
	}

	public static Comment toEntity(CommentDto commentDto, Post post) {
		return Comment.builder()
			.commentId(commentDto.getCommentId())
			.post(post)
			.userId(commentDto.getUserId())
			.content(commentDto.getContent())
			.likes(Math.max(commentDto.getLikes(), 0))
			.dislikes(Math.max(commentDto.getDislikes(), 0))
			.createAt(commentDto.getCommentId() == null ? Instant.now() : commentDto.getCreateAt())
			.updateAt(commentDto.getCommentId() == null ? null : Instant.now())
			.build();
	}
}