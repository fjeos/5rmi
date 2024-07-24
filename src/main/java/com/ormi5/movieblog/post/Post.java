package com.ormi5.movieblog.post;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Getter
@Builder
@Entity
@Table(name = "post")
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id", nullable = false)
    private Integer postId;

    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "is_shared", nullable = false)
    private Boolean isShared = false;

    @Column(name = "likes_count", nullable = false)
    private Integer likesCount;

    @Column(name = "create_at", nullable = false)
    private Instant createAt;

    @Column(name = "update_at")
    private Instant updateAt;
}
