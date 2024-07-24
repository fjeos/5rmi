package com.ormi5.movieblog.comment;

import com.ormi5.movieblog.post.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter



public class Comment {
    private Long id;
    private String content;
    private String author;
    private LocalDateTime createdAt;
    private Post boardPost;

}