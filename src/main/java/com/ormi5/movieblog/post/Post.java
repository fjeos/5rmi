package com.ormi5.movieblog.post;

import com.ormi5.movieblog.comment.Comment;
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
	private Long postId;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@Column(name = "title", nullable = false)
	private String title;

	@Column(name = "content", nullable = false)
	private String content;

	@Builder.Default // @Builder.Default: 빌더 사용 시에도 기본값 적용
	@Column(name = "is_shared", nullable = false)
	// @Column(name = "is_shared", nullable = false, columnDefinition = "boolean default true") // 데이터베이스 레벨에서 기본값 설정
	private Boolean isShared = false;

	@Builder.Default
	@Column(name = "likes_count", nullable = false)
	private int likesCount = 0;

	@Builder.Default
	@Column(name = "create_at", nullable = false, updatable = false) // updatable가 false면 초기화 이후 수정 불가 (읽기 전용 필드)
	private Instant createAt = Instant.now();

	@Column(name = "update_at")
	private Instant updateAt;

	@OneToMany(mappedBy = "post")
	private List<Comment> comments;

	public void updatePost(PostDto postDto) {
		this.title = postDto.getTitle();
		this.content = postDto.getContent();
		this.updateAt = Instant.now();
	}
}