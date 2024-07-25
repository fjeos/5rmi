package com.ormi5.movieblog.comment;

import com.ormi5.movieblog.post.Post;

import lombok.*;

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
		Comment.CommentBuilder commentBuilder
			= Comment.builder()
			.commentId(commentDto.getId())
			.post(post)
			.userId(commentDto.getUserId())
			.content(commentDto.getContent())
			.likes(commentDto.getLikes())
			.dislikes(commentDto.getDislikes())
			.updateAt(Instant.now());

		if (commentDto.getCreateAt() == null) { // null값은 생성되지 않았음을 의미 (새 댓글 생성)
			commentBuilder.createAt(Instant.now()); // 현재 시간으로 설정
		} else { // // 기존 댓글을 사용(수정)하는 상황
			commentBuilder.createAt(commentDto.getCreateAt()); // createAt은 댓글이 생성 시의 값 유지
		}

		return commentBuilder.build();
	}
}