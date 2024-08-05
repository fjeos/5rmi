package com.ormi5.movieblog.postboard;

import com.ormi5.movieblog.post.PostDto;
import com.ormi5.movieblog.postcontroller.PostService;
import com.ormi5.movieblog.user.User;
import com.ormi5.movieblog.user.UserDto;
import com.ormi5.movieblog.usercontroller.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.security.Principal;
import java.util.ArrayList;
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
    public String getBoard(Model model, Principal principal, String searchOption, String keyword) {
        User user = null;
        if(principal != null) {
            user = userService.findByUsername(principal.getName());
        }

        List<PostDto> posts = null;
        if(keyword == null || searchOption == null) {
            posts = postService.getAllPosts();
        }
        else {
            if(searchOption.equals("title"))
                posts = postService.getPostByKeyword(keyword);
            else if(searchOption.equals("user"))
            {
                List<UserDto> user_search = userService.getUsersByUsername(keyword);
                if(user_search != null)
                {
                    posts = new ArrayList<>();

                    for(UserDto u : user_search)
                    {
                        posts.addAll(postService.getPostsByUserId(Long.valueOf(u.getId())));
                    }
                }
            }
            else if(searchOption.equals("movie"))
                posts = postService.getPostsByMovieName(keyword);
        }

        model.addAttribute("user", user);
        model.addAttribute("posts", posts);
        //model.addAttribute("announcements", announcements);

        model.addAttribute("searchOptions", searchOptions());

        return "postboard";
    }

    @ModelAttribute("searchOptions")
    public List<String> searchOptions() {
        List<String> searchOptions = new ArrayList<>();
        searchOptions.add("title");
        searchOptions.add("user");
        searchOptions.add("movie");
        return searchOptions;
    }
}
