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
	private Long id;
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
			.id(post.getPostId())
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
			.postId(postDTO.getId())
			.userId(postDTO.getUserId())
			.title(postDTO.getTitle())
			.content(postDTO.getContent())
			.isShared(postDTO.getIsShared())
			.likesCount(postDTO.getLikesCount())
			.comments(postDTO.comments)
			.createAt(Instant.now())
			.updateAt(Instant.now())
			.build();
	}
}