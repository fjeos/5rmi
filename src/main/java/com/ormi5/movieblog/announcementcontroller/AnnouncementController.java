package com.ormi5.movieblog.announcementcontroller;

import com.ormi5.movieblog.announcement.AnnouncementDto;
import com.ormi5.movieblog.post.PostDto;
import com.ormi5.movieblog.user.User;
import com.ormi5.movieblog.usercontroller.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/announcement")
public class AnnouncementController {
    private final UserService userService;
    private final AnnouncementService announcementService;

    @Autowired
    public AnnouncementController(UserService userService, AnnouncementService announcementService) {
        this.userService = userService;
        this.announcementService = announcementService;
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

    @GetMapping("/{announcementId}/edit")
    public String editAnnouncement(@PathVariable Integer announcementId, Principal principal, Model model) {
        AnnouncementDto announcement = announcementService.getAnnouncement(announcementId);
        User user = userService.findByUsername(principal.getName());

        if (!user.getOp()) {
            throw new RuntimeException("게시글을 수정할 권한이 없습니다");
        }

        model.addAttribute("announcement", announcement);
        return "announcement/edit";
    }

    @PostMapping("/{announcementId}/edit")
    public String updateAnnouncement(@PathVariable("announcementId") Integer id, Principal principal, @ModelAttribute AnnouncementDto updateAnnouncementDto) {
        AnnouncementDto announcement = announcementService.getAnnouncement(id);
        User user = userService.findByUsername(principal.getName());

        if (!user.getOp()) {
            throw new RuntimeException("게시글을 수정할 권한이 없습니다");
        }

        userService.updateAnnouncement(id, updateAnnouncementDto);
        return "redirect:/board";
    }

    @PostMapping("/{id}/delete")
    public String deleteAnnouncement(@PathVariable Integer id, Principal principal) {
        AnnouncementDto announcement = announcementService.getAnnouncement(id);
        User user = userService.findByUsername(principal.getName());

        if (!user.getOp()) {
            throw new RuntimeException("공지글을 삭제할 권한이 없습니다");
        }

        announcementService.deleteAnnouncement(id);
        return "redirect:/board";
    }
}
