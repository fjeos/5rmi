package com.ormi5.movieblog.comment;

import com.ormi5.movieblog.post.Post;
import com.ormi5.movieblog.post.PostDto;
import com.ormi5.movieblog.user.User;
import com.ormi5.movieblog.user.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for {@link Comment}
 */
@Value
@AllArgsConstructor
@Builder
public class CommentDto implements Serializable {
    Integer id;
    UserDto user;
    String content;
    Integer likes;
    Integer dislikes;
    Instant createAt;
    Instant updateAt;
    PostDto post;

    public static CommentDto toDto(Comment comment) {
        return CommentDto.builder()
                .id(comment.getId())
                .post(PostDto.toDto(comment.getPost()))
                .user(UserDto.fromEntity(comment.getUser()))
                .content(comment.getContent())
                .likes(comment.getLikes())
                .dislikes(comment.getDislikes())
                .createAt(comment.getCreateAt())
                .updateAt(comment.getUpdateAt())
                .build();
    }

    public Comment toEntity() {
        return Comment.builder()
                .id(this.getId())
                .post(this.post.toEntity())
                .user(this.getUser().toEntity())
                .content(this.getContent())
                .likes(Math.max(this.getLikes(), 0))
                .dislikes(Math.max(this.getDislikes(), 0))
                .createAt(this.getId() == null ? Instant.now() : this.getCreateAt())
                .updateAt(this.getId() == null ? null : Instant.now())
                .build();
    }
}