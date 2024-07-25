package com.ormi5.movieblog.commentcontroller;

import lombok.*;

@Getter
@Builder
public class DeleteCommentDto {
    private Long commentId;
    private Long userId;

    public static DeleteCommentDto from(Long commentId, Long userId) {
        return DeleteCommentDto.builder()
                .commentId(commentId)
                .userId(userId)
                .build();
    }
}
