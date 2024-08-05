package com.ormi5.movieblog.banhammer;

import com.ormi5.movieblog.post.Post;
import com.ormi5.movieblog.post.PostDto;
import com.ormi5.movieblog.user.User;
import com.ormi5.movieblog.user.UserDto;
import com.ormi5.movieblog.usercontroller.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class BanHammerController {
    private final UserService userService;

    public BanHammerController(UserService userService) {
        this.userService = userService;
    }

    // 모든 게시글을 조회하는 메서드
    @GetMapping("/admin")
    public String getBoard(Model model, Principal principal, @RequestParam(required = false) Integer userId) {
        User user = null;
        if (principal != null) {
            user = userService.findByUsername(principal.getName());

            if(!user.getOp()) {
                // reject
                return "redirect:/board"; // workaround for when not using custom UserDetails for roles
            }

            if(userId != null) {
                User targetUser = userService.findByUserId(userId).get();
                log.info("Triggered ban toggle from {}", targetUser.getOp());
                model.addAttribute("toggleReturn", userService.toggleBan(targetUser.getId()));
            }

            model.addAttribute("users", userService.getAllUsers());

            return "admin/admin";
        }

        return "login/login";
    }
}
