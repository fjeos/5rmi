package com.ormi5.movieblog.announcementcontroller;

import com.ormi5.movieblog.announcement.Announcement;
import com.ormi5.movieblog.announcement.AnnouncementDto;
import com.ormi5.movieblog.post.Post;
import com.ormi5.movieblog.post.PostDto;
import com.ormi5.movieblog.user.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class AnnouncementService {

    private final AnnouncementRepository announcementRepository;

    @Autowired
    public AnnouncementService(AnnouncementRepository announcementRepository) {
        this.announcementRepository = announcementRepository;
    }

    @Transactional
    public void createAnnouncement(AnnouncementDto announcementDto, UserDto userDto) {
        Announcement announcement = Announcement.builder()
                .adminId(userDto.getId())
                .title(announcementDto.getTitle())
                .content(announcementDto.getContent())
                .isImportant(announcementDto.getIsImportant())
                .viewsCount(0)
                .createAt(Instant.now())
                .build();

        announcementRepository.save(announcement);
    }

    @Transactional(readOnly = true)
    public AnnouncementDto getAnnouncement(Integer id) {
       return announcementRepository.findById(id)
                .map(AnnouncementDto::toDto)
                .orElseThrow(() -> new RuntimeException("Announcement not found"));
    }

    @Transactional(readOnly = true)
    public List<AnnouncementDto> getAllAnnouncements() {
        return announcementRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public void updateAnnouncement(Integer id, AnnouncementDto announcementDto) {
        Announcement announcement = announcementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Announcement not found"));

        announcement.updateAnnouncement(announcementDto);
        announcementRepository.save(announcement);
    }

    @Transactional
    public void deleteAnnouncement(Integer id) {
        announcementRepository.deleteById(id);
    }

    private AnnouncementDto convertToDto(Announcement announcement) {
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
}
