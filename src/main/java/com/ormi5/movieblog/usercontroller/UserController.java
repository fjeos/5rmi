package com.ormi5.movieblog.usercontroller;

import com.ormi5.movieblog.user.ProfileResponseDto;
import com.ormi5.movieblog.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {
	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/{userId}")
	public String getUserProfile(@PathVariable("userId") Integer userId, Model model) {
		model.addAttribute("user", userService.getUserProfile(userId));

		return "user/user";
	}

	/**
	 * 회원 탈퇴를 실행하는 메서드
	 * @param principal 현재 로그인한 사용자의 username을 가져옴
	 * @return 메인 페이지로 이동
	 */
	@PostMapping("/delete")
	public String leaveMember(Principal principal) {
		userService.leaveMember(principal.getName());
		// 유저를 삭제한 후 강제 로그아웃
		new SecurityContextLogoutHandler().logout(((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest(), null, null);
		return "/home";
	}
}