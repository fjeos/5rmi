package com.ormi5.movieblog.comment;

import com.ormi5.movieblog.post.Post;
import com.ormi5.movieblog.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "content", length = 256)
    private String content;

    @Column(name = "likes")
    private Integer likes;

    @Column(name = "dislikes")
    private Integer dislikes;

    @Column(name = "create_at")
    private Instant createAt;

    @Column(name = "update_at")
    private Instant updateAt;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

	public void updateComment(CommentDto commentDto) {
		this.content = commentDto.getContent();
		this.updateAt = Instant.now();
	}

	public void increaseLike() {
		this.likes++;
	}

	public void decreaseLike() {
		this.dislikes++;
	}
}