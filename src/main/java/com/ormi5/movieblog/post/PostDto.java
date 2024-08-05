package com.ormi5.movieblog.post;

import com.ormi5.movieblog.comment.Comment;
import com.ormi5.movieblog.comment.CommentDto;
import com.ormi5.movieblog.user.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

/**
 * DTO for {@link Post}
 */
@Value
@AllArgsConstructor
@Builder
public class PostDto implements Serializable {
    Long postId;
    UserDto user;
    String title;
    String content;
    Boolean isShared;
    int likesCount;
    Instant createAt;
    Instant updateAt;
    List<CommentDto> comments;

    public static PostDto toDto(Post post) {
        return PostDto.builder()
                .postId(post.getPostId())
                .user(UserDto.fromEntity(post.getUser()))
                .title(post.getTitle())
                .content(post.getContent())
                .isShared(post.getIsShared())
                .likesCount(post.getLikesCount())
                .createAt(post.getCreateAt())
                .updateAt(post.getUpdateAt())
                .comments(post.getComments().stream().map(comment -> {return CommentDto.toDto(comment);}).collect(Collectors.toList()))
                .build();
    }

    public Post toEntity() {
        return Post.builder()
                .postId(this.getPostId())
                .user(this.getUser().toEntity())
                .title(this.getTitle())
                .content(this.getContent())
                .isShared(this.getIsShared() != null && this.getIsShared())
                // .isShared(postDTO.getIsShared() == null ? false : postDTO.getIsShared())
                .likesCount(Math.max(this.getLikesCount(), 0))
                // .likesCount(postDTO.getLikesCount() >=0 ? postDTO.getLikesCount() : 0)
                /* postDTO.getId()의 값이 null이라는 건 게시글을 생성한다는 뜻 */
                .createAt(this.getPostId() == null ? Instant.now() : this.getCreateAt())
                .updateAt(this.getPostId() == null ? null : Instant.now())
                .build();
    }
}