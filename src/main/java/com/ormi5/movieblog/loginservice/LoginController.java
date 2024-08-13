package com.ormi5.movieblog.loginservice;

import com.ormi5.movieblog.announcementcontroller.AnnouncementService;
import com.ormi5.movieblog.user.User;
import com.ormi5.movieblog.user.UserDto;
import com.ormi5.movieblog.usercontroller.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class LoginController {
    private final UserService userService;
    private final LoginService loginService;
    private final AnnouncementService announcementService;

    @GetMapping("/login")
    public String login(Model model, Principal principal, LoginRequest loginRequest){
        if(loginRequest == null) {
            loginRequest = new LoginRequest();
        }

        model.addAttribute("user", loginRequest);
        return "login/login";
    }

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("user", new User());
        return "login/register";
    }

    @PostMapping("/register")
    public String registerNewUser(@ModelAttribute("user") UserDto userDto, Model model) {
        User user = userService.findByUsername(userDto.getUsername());
        if (user != null) {
            model.addAttribute("userexist", user);
            return "login/register";
        }
        User emailuser = userService.findByEmail(userDto.getEmail());
        if (emailuser != null) {
            model.addAttribute("emailexist", emailuser);
            return "login/register";
        }
        
        userService.addUser(userDto);

        return "redirect:/login?success";
    }

    @GetMapping("/banned")
    public String banned(Model model){
        model.addAttribute("announcements", announcementService.getAllAnnouncements());
        return "login/banned";
    }
}
