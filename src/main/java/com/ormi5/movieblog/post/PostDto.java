package com.ormi5.movieblog.post;

import java.io.Serializable;
import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostDto implements Serializable {
	Integer id;
	Integer userId;
	String author;
	String title;
	String content;
	Boolean isShared;
	Integer likesCount;
	Instant createAt;
	Instant updateAt;

	// Entity를 DTO로 변환
	public static PostDto toDto(Post post) {
		return PostDto.builder()
			.id(post.getPostId())
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
	public static Post toEntity(PostDto postDto) {
		return Post.builder()
			.postId(postDto.getId())
			.userId(postDto.getUserId())
			.author(postDto.getAuthor())
			.title(postDto.getTitle())
			.content(postDto.getContent())
			.isShared(postDto.getIsShared())
			.likesCount(postDto.getLikesCount())
			.createAt(Instant.now())
			.updateAt(Instant.now())
			.build();
	}
}