package com.ormi5.movieblog.announcementcontroller;

import com.ormi5.movieblog.announcement.AnnouncementDto;
import com.ormi5.movieblog.post.PostDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/announcement")
public class AnnouncementController {

    private final AnnouncementService announcementService;

    @Autowired
    public AnnouncementController(AnnouncementService announcementService) {
        this.announcementService = announcementService;
    }

    @PostMapping
    public ResponseEntity<AnnouncementDto> createAnnouncement(@RequestBody AnnouncementDto announcementDto) {
        AnnouncementDto createdAnnouncement = announcementService.createAnnouncement(announcementDto);
        return new ResponseEntity<>(createdAnnouncement, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public String getAnnouncement(@PathVariable("id") Integer announcementId, Model model) {
        AnnouncementDto announcement = announcementService.getAnnouncement(announcementId);
        model.addAttribute("announcement", announcement);

        return "announcement/detail";
    }

//    /**
//     * 특정 게시글 조회: 게시글의 ID를 받아 해당 게시글 조회
//     *
//     * @author yuseok, nayoung
//     * @param postId 조회할 게시글의 ID
//     * @return String, post/detail 링크로 이동
//     */
//    @GetMapping("/{id}")
//    public String getPostById(@PathVariable("id") Integer postId, Model model) {
//        PostDto post = postService.getPostById(postId);
//        model.addAttribute("post", post);
//
//        return "post/detail";
//    }

    @GetMapping
    public ResponseEntity<List<AnnouncementDto>> getAllAnnouncements() {
        List<AnnouncementDto> announcements = announcementService.getAllAnnouncements();
        return ResponseEntity.ok(announcements);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnnouncementDto> updateAnnouncement(@PathVariable Integer id, @RequestBody AnnouncementDto announcementDto) {
        AnnouncementDto updatedAnnouncement = announcementService.updateAnnouncement(id, announcementDto);
        return ResponseEntity.ok(updatedAnnouncement);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnnouncement(@PathVariable Integer id) {
        announcementService.deleteAnnouncement(id);
        return ResponseEntity.noContent().build();
    }
}
