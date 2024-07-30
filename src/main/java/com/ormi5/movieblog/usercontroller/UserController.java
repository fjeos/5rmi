package com.ormi5.movieblog.usercontroller;

import com.ormi5.movieblog.loginservice.LoginRequest;
import com.ormi5.movieblog.loginservice.LoginService;
import com.ormi5.movieblog.user.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@Controller
@RequestMapping("/")
public class UserController {
	private final LoginService loginService;
	private final UserService UserService;

	@Autowired
	public UserController(LoginService loginService, UserService UserService) {
        this.loginService = loginService;
        this.UserService = UserService;
	}

	@GetMapping
	public String home(Model model, Principal principal) {
		UserDetails userDetails = loginService.loadUserByUsername(principal.getName());
		model.addAttribute("userdetail", userDetails);
		return "home";
	}

	@GetMapping("/login")
	public String login(Model model, LoginRequest loginRequest) {
		if(loginRequest == null) {
			loginRequest = new LoginRequest();
		}

		model.addAttribute("user", loginRequest);
		return "login";
	}

	//register
}