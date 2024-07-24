package com.ormi5.movieblog.post;

import java.time.Instant;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Table(name = "post")
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "post_id", nullable = false)
	private Long postId;

	@Column(name = "user_id", nullable = false)
	private Long userId;

	@Column(name = "author", nullable = false)
	private String author;

	@Column(name = "title", nullable = false)
	private String title;

	@Column(name = "content", nullable = false)
	private String content;

	@Column(name = "is_shared", nullable = false)
	private Boolean isShared = false;

	@Column(name = "likes_count", nullable = false)
	private int likesCount;

	@Column(name = "create_at", nullable = false)
	private Instant createAt;

	@Column(name = "update_at")
	private Instant updateAt;
}