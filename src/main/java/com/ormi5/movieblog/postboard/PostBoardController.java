package com.ormi5.movieblog.postboard;

import com.ormi5.movieblog.announcement.Announcement;
import com.ormi5.movieblog.announcement.AnnouncementDto;
import com.ormi5.movieblog.announcementcontroller.AnnouncementService;
import com.ormi5.movieblog.post.Post;
import com.ormi5.movieblog.post.PostDto;
import com.ormi5.movieblog.postcontroller.PostService;
import com.ormi5.movieblog.user.User;
import com.ormi5.movieblog.user.UserDto;
import com.ormi5.movieblog.usercontroller.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class PostBoardController {
    private final PostService postService;
    private final UserService userService;
    private final AnnouncementService announcementService;
//    private final MovieService movieService;

    // 모든 게시글을 조회하는 메서드
    @GetMapping("/board")
    public String getBoard(Model model, Principal principal, String searchOption, String keyword) {
        User user = null;

        // announcement를 출력하는 부분은 공통이기 때문에 상단부에 선언
        model.addAttribute("announcements", announcementService.getAllAnnouncements());

        if (principal != null) {
            user = userService.findByUsername(principal.getName());

            if(user.getIsStop())
            {
                new SecurityContextLogoutHandler().logout(((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest(), null, null);
                return "login/banned";
            }
        }

        List<PostDto> posts = null;
        if (keyword == null || searchOption == null) {
            posts = postService.getAllPosts();
        } else {
            if (searchOption.equals("title"))
                posts = postService.getPostByKeyword(keyword);
            else if (searchOption.equals("user")) {
                List<UserDto> user_search = userService.getUsersByUsername(keyword);
                if (user_search != null) {
                    posts = new ArrayList<>();

                    for (UserDto u : user_search) {
                        posts.addAll(postService.getPostsByUserId(u.getId()));
                    }
                }
            } else if (searchOption.equals("movie"))
                posts = postService.getPostsByMovieName(keyword);
        }

        model.addAttribute("user", user);
        model.addAttribute("posts", posts);

        model.addAttribute("searchOptions", searchOptions());

        return "postboard";
    }

    public List<String> searchOptions() {
        List<String> searchOptions = new ArrayList<>();
        searchOptions.add("title");
        searchOptions.add("user");
        searchOptions.add("movie");
        return searchOptions;
    }

    @GetMapping("/newpost")
    public String newPost(Model model, Principal principal) {
        Post post = new Post();

        model.addAttribute("post", post);
        return "post/newpost";
    }

    @PostMapping("/newpost")
    public String newPost(@ModelAttribute("post") PostDto postDto, Principal principal, Model model) {
        User user = null;
        if (principal != null) {
            user = userService.findByUsername(principal.getName());

            log.info("New post for {}", user.getUsername());
//        Movie movie = (userDto.getEmail());
//        if (emailuser != null) {
//            model.addAttribute("", emailuser);
//            return "post/newpost";
//        }
            // TODO Connect with movie service

            postService.createPost(postDto, UserDto.fromEntity(user));
            return "redirect:/board";
        }

        return "post/newpost";
    }

    @GetMapping("/newannouncement")
    public String createAnnouncement(Model model, Principal principal) {
        Announcement announcement = new Announcement();
        User user = null;
        if(principal != null) {
            user = userService.findByUsername(principal.getName());

            if(!user.getOp()) {
                throw new RuntimeException("게시글을 추가할 권한이 없습니다");
            }
        }


        model.addAttribute("announcement", announcement);
        return "announcement/newannouncement";
    }

    @PostMapping("/newannouncement")
    public String createAnnouncement(@ModelAttribute("announcement") AnnouncementDto announcementDto,  Principal principal, Model model) {
        User user = null;
        if(principal != null) {
            user = userService.findByUsername(principal.getName());

            log.info("New announcement for {}", user.getUsername());

            announcementService.createAnnouncement(announcementDto, UserDto.fromEntity(user));
            return "redirect:/board";
        }

        return "announcement/newannouncement";
    }
}
