package com.ormi5.movieblog.post;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

@Getter
@Builder
public class ProfilePostResponseDto {
    private Integer postId;
    private Integer userId;
    private String title;
    private String content;
    private Integer likesCount;
    private Instant createAt;
    private Instant updateAt;
    private Boolean isShared;

    public static ProfilePostResponseDto toDto(Post post) {
        return ProfilePostResponseDto.builder()
                .postId(post.getPostId())
                .userId(post.getUser().getId())
                .title(post.getTitle())
                .content(post.getContent())
                .likesCount(post.getLikesCount())
                .createAt(post.getCreateAt())
                .updateAt(post.getUpdateAt())
                .isShared(post.getIsShared())
                .build();
    }
}
