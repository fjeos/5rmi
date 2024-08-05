package com.ormi5.movieblog.announcementcontroller;

import com.ormi5.movieblog.announcement.Announcement;
import com.ormi5.movieblog.announcement.AnnouncementDto;
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
    public AnnouncementDto createAnnouncement(AnnouncementDto announcementDto) {
        Announcement announcement = Announcement.builder()
                .adminId(announcementDto.getAdminId())
                .title(announcementDto.getTitle())
                .content(announcementDto.getContent())
                .isImportant(announcementDto.getIsImportant())
                .viewsCount(0)
                .createAt(Instant.now())
                .build();

        Announcement savedAnnouncement = announcementRepository.save(announcement);
        return convertToDto(savedAnnouncement);
    }

    @Transactional(readOnly = true)
    public AnnouncementDto getAnnouncement(Integer id) {
        Announcement announcement = announcementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Announcement not found"));
        return convertToDto(announcement);
    }

    @Transactional(readOnly = true)
    public List<AnnouncementDto> getAllAnnouncements() {
        return announcementRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public AnnouncementDto updateAnnouncement(Integer id, AnnouncementDto announcementDto) {
        Announcement announcement = announcementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Announcement not found"));

        announcement.updateAnnouncement(announcementDto);
        Announcement updatedAnnouncement = announcementRepository.save(announcement);
        return convertToDto(updatedAnnouncement);
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
