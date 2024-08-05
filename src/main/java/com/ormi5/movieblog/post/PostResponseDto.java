package com.ormi5.movieblog.post;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostResponseDto {
    private String title;
    private String content;
    private Boolean isShared;
}
