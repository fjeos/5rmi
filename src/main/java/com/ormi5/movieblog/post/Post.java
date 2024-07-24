package com.ormi5.movieblog.post;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "post")
@Data
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @Column(nullable = false)
    private Long userId;

    @Column(length = 32, nullable = false)
    private String author;

    @Column(length = 256, nullable = false)
    private String title;

    @Column(length = 4096, nullable = false)
    private String content;

    private Boolean isShared;

    private Integer comments;

    private Integer likesCount;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;
}