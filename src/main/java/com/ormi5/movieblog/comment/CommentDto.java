package com.ormi5.movieblog.comment;

import com.ormi5.movieblog.post.Post;

import com.ormi5.movieblog.post.PostDto;
import com.ormi5.movieblog.user.UserDto;
import lombok.*;

import java.time.Instant;

@Getter
@Builder
public class CommentDto {
	private Integer commentId;
	private PostDto post;
	private UserDto user;
	private String content;
	private int likes;
	private int dislikes;
	private Instant createAt;
	private Instant updateAt;

	public static CommentDto toDto(Comment comment) {
		return CommentDto.builder()
			.commentId(comment.getId())
			.post(PostDto.toDto(comment.getPost()))
			.user(UserDto.fromEntity(comment.getUser()))
			.content(comment.getContent())
			.likes(comment.getLikes())
			.dislikes(comment.getDislikes())
			.createAt(comment.getCreateAt())
			.updateAt(comment.getUpdateAt())
			.build();
	}

	public Comment toEntity() {
		return Comment.builder()
			.id(this.getCommentId())
			.post(this.getPost().toEntity())
			.user(this.getUser().toEntity())
			.content(this.getContent())
			.likes(Math.max(this.getLikes(), 0))
			.dislikes(Math.max(this.getDislikes(), 0))
			.createAt(this.getCommentId() == null ? Instant.now() : this.getCreateAt())
			.updateAt(this.getCommentId() == null ? null : Instant.now())
			.build();
	}
}