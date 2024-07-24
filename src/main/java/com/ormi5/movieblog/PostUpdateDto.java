package com.ormi5.movieblog;

import lombok.Data;

@Data
public class PostUpdateDto {
    private String title;
    private String content;
    private Boolean isShared;
}
