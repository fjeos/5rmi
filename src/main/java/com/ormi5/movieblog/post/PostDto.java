package com.ormi5.movieblog.post;

import com.ormi5.movieblog.comment.Comment;

import lombok.*;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
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
	private LocalDateTime createAt;
	private LocalDateTime updateAt;
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
			.isShared(postDTO.getIsShared() != null && postDTO.getIsShared())
			// .isShared(postDTO.getIsShared() == null ? false : postDTO.getIsShared())
			.likesCount(Math.max(postDTO.getLikesCount(), 0))
			// .likesCount(postDTO.getLikesCount() >=0 ? postDTO.getLikesCount() : 0)
			/* postDTO.getId()의 값이 null이라는 건 게시글을 생성한다는 뜻 */
			.createAt(postDTO.getId() == null ? LocalDateTime.now() : postDTO.getCreateAt())
			.updateAt(postDTO.getId() == null ? null : LocalDateTime.now())
			.comments(postDTO.comments)
			.build();
	}
}