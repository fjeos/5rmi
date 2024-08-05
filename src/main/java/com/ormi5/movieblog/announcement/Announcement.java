package com.ormi5.movieblog.announcement;

import lombok.*;
import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Announcement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "announcement_id", nullable = false)
    private Long announcementId;

    @Column(name = "admin_id", nullable = false)
    private Long adminId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "is_important", nullable = false)
    private Boolean isImportant = false;

    @Column(name = "views_count", nullable = false)
    private int viewsCount;

    @Column(name = "create_at", nullable = false)
    private Instant createAt;

    @Column(name = "update_at")
    private Instant updateAt;

    public void updateAnnouncement(AnnouncementDto announcementDto) {
        this.title = announcementDto.getTitle();
        this.content = announcementDto.getContent();
        this.isImportant = announcementDto.getIsImportant();
        this.updateAt = Instant.now();
    }
}



