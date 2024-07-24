package com.ormi5.movieblog.post;

import lombok.Value;

import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for {@link Post}
 */
@Value
public class PostDto implements Serializable {
    Integer id;
    Integer userId;
    String author;
    String title;
    String content;
    Boolean isShared;
    Integer likesCount;
    Instant createAt;
    Instant updateAt;
}