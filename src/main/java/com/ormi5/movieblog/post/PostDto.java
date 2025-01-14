package com.ormi5.movieblog.post;

import com.ormi5.movieblog.comment.Comment;
import com.ormi5.movieblog.comment.CommentDto;
import com.ormi5.movieblog.movie.MovieDto;
import com.ormi5.movieblog.user.UserDto;
import lombok.*;

import javax.swing.text.html.parser.Entity;
import java.io.Serializable;
import java.time.Instant;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

/**
 * DTO for {@link Post}
 */
@Value
@Getter
@AllArgsConstructor
@Builder
public class PostDto implements Serializable {
    Integer postId;
    UserDto user;
    String title;
    String content;
    Boolean isShared;
    Integer likesCount;
    Instant createAt;
    Instant updateAt;
    List<CommentDto> comments;
    MovieDto movieId;


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
                .comments(post.getComments() == null? null : post.getComments().stream().map(CommentDto::toDto).collect(Collectors.toList()))
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
                .createAt(this.getPostId() == null ? Instant.now().atZone(ZoneId.of("Asia/Seoul")).toInstant() : this.getCreateAt())
                .updateAt(this.getPostId() == null ? null : Instant.now().atZone(ZoneId.of("Asia/Seoul")).toInstant())
                .build();
    }
}