package com.ormi5.movieblog.comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.ZoneId;

import com.ormi5.movieblog.post.Post;

@Getter
@Builder
public class CommentDto {
	private Long id;
	private Long postId;
	private Long userId;
	private String author;
	private String content;
	private int likes;
	private int dislikes;
	private Instant createAt;
	private Instant updateAt;

	public static CommentDto toDto(Comment comment) {
		return CommentDto.builder()
			.id(comment.getCommentId())
			// .postId(comment.getPostID())
			.postId(comment.getPost().getPostId())
			.userId(comment.getUserId())
			.author(comment.getAuthor())
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
			.author(commentDto.getAuthor())
			.content(commentDto.getContent())
			.likes(commentDto.getLikes())
			.dislikes(commentDto.getDislikes())
			.createAt(Instant.now())
			.updateAt(Instant.now())
			.build();
	}
}