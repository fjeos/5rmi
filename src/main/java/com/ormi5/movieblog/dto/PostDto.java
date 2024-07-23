package com.ormi5.movieblog.dto;

import java.time.LocalDateTime;

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
public class PostDto {
	private Long postId;
	private Long userId;
	private String author;
	private String title;
	private String content;
	private Boolean isShared;
	private int likesCount;
	private LocalDateTime createAt;
	private LocalDateTime updateAt;
}