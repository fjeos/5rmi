package com.ormi5.movieblog.post;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

@Getter
@Builder
public class ProfilePostResponseDto {
    private Long postId;
    private Long userId;
    private String title;
    private String content;
    private int likesCount;
    private Instant createAt;

    public static ProfilePostResponseDto toDto(Post post) {
        return ProfilePostResponseDto.builder()
                .postId(post.getPostId())
                .userId(post.getUserId())
                .title(post.getTitle())
                .content(post.getContent())
                .likesCount(post.getLikesCount())
                .createAt(post.getCreateAt())
                .build();
    }
}
