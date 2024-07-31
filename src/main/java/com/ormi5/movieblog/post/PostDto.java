package com.ormi5.movieblog.post;

import com.ormi5.movieblog.comment.Comment;

import lombok.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

/**
 * DTO for {@link Post}
 */
@Getter
@Builder
public class PostDto implements Serializable {
	private Long postId;
	private Long userId;
	private String title;
	private String content;
	private Boolean isShared;
	private int likesCount;
	private Instant createAt;
	private Instant updateAt;
	private List<Comment> comments;

	public static PostDto toDto(Post post) {
		return PostDto.builder()
			.postId(post.getPostId())
			.userId(post.getUserId())
			.title(post.getTitle())
			.content(post.getContent())
			.isShared(post.getIsShared())
			.likesCount(post.getLikesCount())
			.createAt(post.getCreateAt())
			.updateAt(post.getUpdateAt())
			.comments(post.getComments())
			.build();
	}

	public static Post toEntity(PostDto postDTO) {
		return Post.builder()
			.postId(postDTO.getPostId())
			.userId(postDTO.getUserId())
			.title(postDTO.getTitle())
			.content(postDTO.getContent())
			.isShared(postDTO.getIsShared() != null && postDTO.getIsShared())
			.likesCount(Math.max(postDTO.getLikesCount(), 0))
			.createAt(postDTO.getPostId() == null ? Instant.now() : postDTO.getCreateAt())
			.updateAt(postDTO.getPostId() == null ? null : Instant.now())
			.comments(postDTO.comments)
			.build();
	}
}