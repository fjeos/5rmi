package com.ormi5.movieblog.post;

import lombok.*;
import lombok.Value;

import java.io.Serializable;
import java.time.Instant;

@Value
@Builder
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

    public static PostDto toPostDTO(Post post) {
        return PostDto.builder()
                .id(post.getPostId())
                .userId(post.getUserId())
                .author(post.getAuthor())
                .title(post.getTitle())
                .content(post.getContent())
                .isShared(post.getIsShared())
                .likesCount(post.getLikesCount())
                .createAt(post.getCreateAt())
                .updateAt(post.getUpdateAt())
                .build();
    }

    public static Post toPostEntity(PostDto postDTO) {
        return Post.builder()
                .postId(postDTO.getId())
                .title(postDTO.getTitle())
                .content(postDTO.getContent())
                .isShared(postDTO.getIsShared())
                .likesCount(postDTO.getLikesCount())
                .createAt(postDTO.getCreateAt())
                .updateAt(postDTO.getUpdateAt())
                .build();
    }
}
