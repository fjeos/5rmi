package com.ormi5.movieblog.postboard;

import com.ormi5.movieblog.post.PostDto;
import com.ormi5.movieblog.postcontroller.PostService;
import com.ormi5.movieblog.usercontroller.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class PostBoardController {
    private final PostService postService;
    private final UserService userService;
    //private final AnnouncementService announcementService;

    public PostBoardController(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    // 모든 게시글을 조회하는 메서드
    @GetMapping("/board")
    public String getBoard(Model model, Principal principal) {
        model.addAttribute("user", userService.findByUsername(principal.getName()));

        List<PostDto> posts = postService.getAllPosts();
        model.addAttribute("posts", posts);
        //model.addAttribute("announcements", announcements);

        return "postboard";
    }

}
