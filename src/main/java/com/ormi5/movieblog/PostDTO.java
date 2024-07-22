package com.ormi5.movieblog;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {

    private Long postId;

    private String title;

    private String content;

    private boolean isShared;

    private int likesCount;

    private Date createAt;

    private Date updateAt;

    public static PostDTO toPostDTO(Post post) {
        return PostDTO.builder()
                .postId(post.getPostId())
                .title(post.getTitle())
                .content(post.getContent())
                .isShared(post.isShared())
                .likesCount(post.getLikesCount())
                .createAt(post.getCreateAt())
                .updateAt(post.getUpdateAt())
                .build();
    }

    public static Post toPostEntity(PostDTO postDTO) {
        return Post.builder()
                .postId(postDTO.getPostId())
                .title(postDTO.getTitle())
                .content(postDTO.getContent())
                .isShared(postDTO.isShared())
                .likesCount(postDTO.getLikesCount())
                .createAt(postDTO.getCreateAt())
                .updateAt(postDTO.getUpdateAt())
                .build();
    }
}
