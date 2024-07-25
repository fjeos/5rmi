package com.ormi5.movieblog.comment;

import com.ormi5.movieblog.post.Post;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Entity
@Table(name = "comment")

public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id", nullable = false)
    private Long commentId;

    @Column(name = "post_id", nullable = false)
    private Long postId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "likes", nullable = false)
    private int likes;

    @Column(name = "dislikes", nullable = false)
    private int dislikes;

    @Column(name = "create_at", nullable = false)
    private Instant createAt;

    @Column(name = "update_at")
    private Instant updateAt;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    public void updateComment(CommentDto commentDto) {
        this.content = commentDto.getContent();
        this.updateAt = Instant.now();
    }
}