package com.ormi5.movieblog.announcement;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnnouncementDto {
    private Long announcementId;
    private Long adminId;
    private String title;
    private String content;
    private Boolean isImportant;
    private int viewsCount;
    private Instant createAt;
    private Instant updateAt;
}