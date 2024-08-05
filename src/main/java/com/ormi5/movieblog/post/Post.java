package com.ormi5.movieblog.post;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

import com.ormi5.movieblog.comment.Comment;
import com.ormi5.movieblog.movie.Movie;
import com.ormi5.movieblog.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

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
	private Integer postId;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@Column(name = "title", nullable = false)
	private String title;

	@Column(name = "content", nullable = false)
	private String content;

	@Builder.Default
	@Column(name = "is_shared", nullable = false)
	private Boolean isShared = false;

	@Builder.Default
	@Column(name = "likes_count", nullable = false)
	private int likesCount = 0;

	@Builder.Default
	@Column(name = "create_at", nullable = false, updatable = false) // 읽기 전용 필드
	private Instant createAt = Instant.now();

	@Column(name = "update_at")
	private Instant updateAt;

	@OneToMany(mappedBy = "post")
	private List<Comment> comments;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "movie_id", nullable = false)
	private Movie movieId;

	public void updatePost(PostUpdateDto postDto) {
		this.title = postDto.getTitle();
		this.content = postDto.getContent();
		this.updateAt = Instant.now();
	}
}