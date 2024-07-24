package com.ormi5.movieblog.dto;

import java.time.LocalDateTime;

import com.ormi5.movieblog.entity.Post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {
	private Long postId;
	private Long userId;
	private String author;
	private String title;
	private String content;
	private Boolean isShared;
	private int likesCount;
	private LocalDateTime createAt;
	private LocalDateTime updateAt;

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
			.createAt(LocalDateTime.now())
			.updateAt(LocalDateTime.now())
			.build();
	}
}