package com.ormi5.movieblog.post;

import java.time.Instant;
import java.util.List;

import com.ormi5.movieblog.comment.Comment;



import com.ormi5.movieblog.movie.Movie;

import com.ormi5.movieblog.user.User;
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
	private Integer postId;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@Column(name = "title", nullable = false)
	private String title;

	@Column(name = "content", nullable = false)
	private String content;

	@Column(name = "is_shared", nullable = false)
	private Boolean isShared = false;

	@Column(name = "likes_count", nullable = false)
	private Integer likesCount = 0;

	@Column(name = "create_at", nullable = false, updatable = false) // 읽기 전용 필드
	private Instant createAt = Instant.now();

	@Column(name = "update_at")
	private Instant updateAt;

	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Comment> comments;

	@ManyToOne(fetch = FetchType.LAZY)//, optional = false)
	@JoinColumn(name = "movie_id")
	private Movie movieId;

	public void updatePost(PostResponseDto postDto) {
		this.title = postDto.getTitle();
		this.content = postDto.getContent();
		this.updateAt = Instant.now();
	}
	public void increaseLike() {
		this.likesCount++;
	}
}