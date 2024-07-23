package com.ormi5.movieblog.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
	private Long postId;
	private Long userId;
	private String author;
	private String title;
	private String content;
	private boolean isShared;
	private int likesCount;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}