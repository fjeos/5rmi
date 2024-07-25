package com.ormi5.movieblog.comment;

import com.ormi5.movieblog.post.Post;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

@Getter
@Builder
public class CommentDto {
	private Long id;
	private Long postId;
	private Long userId;
	private String content;
	private int likes;
	private int dislikes;
	private Instant createAt;
	private Instant updateAt;

	public static CommentDto toDto(Comment comment) {
		return CommentDto.builder()
			.id(comment.getCommentId())
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
			.commentId(commentDto.getId())
			.post(post) // post 필드를 CommentDto에서 직접 설정
			.userId(commentDto.getUserId())
			.content(commentDto.getContent())
			.likes(commentDto.getLikes())
			.dislikes(commentDto.getDislikes())
			.createAt(Instant.now())
			.updateAt(Instant.now())
			.build();
	}
}
