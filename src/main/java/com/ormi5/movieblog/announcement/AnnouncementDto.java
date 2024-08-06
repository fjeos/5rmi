package com.ormi5.movieblog.announcement;

import com.ormi5.movieblog.comment.CommentDto;
import com.ormi5.movieblog.post.Post;
import com.ormi5.movieblog.post.PostDto;
import com.ormi5.movieblog.user.UserDto;
import lombok.*;

import java.time.Instant;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
@Builder
/**
 * DTO for {@link Announcement}
 */
@Value
public class AnnouncementDto {
    Integer announcementId;
    Integer adminId;
    String title;
    String content;
    Boolean isImportant;
    Integer viewsCount;
    Instant createAt;
    Instant updateAt;

    public static AnnouncementDto toDto(Announcement announcement) {
        return AnnouncementDto.builder()
                .announcementId(announcement.getAnnouncementId())
                .adminId(announcement.getAdminId())
                .title(announcement.getTitle())
                .content(announcement.getContent())
                .isImportant(announcement.getIsImportant())
                .viewsCount(announcement.getViewsCount())
                .createAt(announcement.getCreateAt())
                .updateAt(announcement.getUpdateAt())
                .build();
    }

    public Announcement toEntity() {
        return Announcement.builder()
                .announcementId(this.announcementId)
                .adminId(this.adminId)
                .title(this.title)
                .content(this.content)
                .isImportant(this.isImportant)
                .viewsCount(this.viewsCount)
                .createAt(this.createAt == null ? Instant.now() : this.getCreateAt())
                .build();
    }
}