package com.ormi5.movieblog.post;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {
	private Integer postId;
	private Integer userId;
	private String author;
	private String title;
	private String content;
	private Boolean isShared;
	private Integer likesCount;
	private Instant createAt;
	private Instant updateAt;

	// Entity를 DTO로 변환
	public static PostDTO toDTO(Post post) {
		return PostDTO.builder()
			.postId(post.getPostId())
			.userId(post.getUserId())
			.author(post.getAuthor())
			.title(post.getTitle())
			.content(post.getContent())
			.isShared(post.getIsShared())
			.likesCount(post.getLikesCount())
			.createAt(post.getCreateAt())
			.updateAt(post.getUpdateAt())
			.build();
	}

	// DTO를 Entity로 변환
	public static Post toEntity(PostDTO postDTO) {
		return Post.builder()
			.postId(postDTO.getPostId())
			.userId(postDTO.getUserId())
			.author(postDTO.getAuthor())
			.title(postDTO.getTitle())
			.content(postDTO.getContent())
			.isShared(postDTO.getIsShared())
			.likesCount(postDTO.getLikesCount())
			.createAt(Instant.now())
			.updateAt(Instant.now())
			.build();
	}
}