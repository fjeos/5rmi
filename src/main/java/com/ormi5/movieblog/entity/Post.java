package com.ormi5.movieblog.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "post")
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "post_id", nullable = false)
	private Long postId;

	@Column(name = "user_id")
	private Long userId;

	@Column(name = "author")
	private String author;

	@Column(name = "title")
	private String title;

	@Column(name = "content")
	private String content;

	@Column(name = "is_shared")
	private Boolean isShared;

	@Column(name = "likes_count")
	private int likesCount;

	@Column(name = "create_at")
	private LocalDateTime createAt;

	@Column(name = "update_at")
	private LocalDateTime updateAt;
}