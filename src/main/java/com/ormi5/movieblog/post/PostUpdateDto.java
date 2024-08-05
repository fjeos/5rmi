package com.ormi5.movieblog.post;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostUpdateDto {

    private String title;
    private String content;
}
