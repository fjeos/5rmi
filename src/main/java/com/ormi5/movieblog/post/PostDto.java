package com.ormi5.movieblog.post;

import lombok.Data;

@Data
public class PostDto {
    private String title;
    private String content;
    private Boolean isShared;
}
