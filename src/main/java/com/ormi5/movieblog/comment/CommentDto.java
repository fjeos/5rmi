package com.ormi5.movieblog.comment;

import lombok.*;
import java.io.Serializable;
import java.time.Instant;

@Getter
@Builder
public class CommentDto implements Serializable {
    private Long id;
    private Long postId;
    private Long userId;
    private String content;
    private Instant createAt;
    private Instant updateAt;

    public static CommentDto toDTO(Comment comment) {
        return CommentDto.builder()
                .id(comment.getCommentId())
                .postId(comment.getPostId())
                .userId(comment.getUserId())
                .content(comment.getContent())
                .createAt(comment.getCreateAt())
                .updateAt(comment.getUpdateAt())
                .build();
    }

    public static Comment toEntity(CommentDto commentDTO) {
        return Comment.builder()
                .commentId(commentDTO.getId())
                .postId(commentDTO.getPostId())
                .userId(commentDTO.getUserId())
                .content(commentDTO.getContent())
                .createAt(Instant.now())
                .updateAt(Instant.now())
                .build();
    }
}
