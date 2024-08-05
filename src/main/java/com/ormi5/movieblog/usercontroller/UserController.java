package com.ormi5.movieblog.usercontroller;

import com.ormi5.movieblog.user.ProfileResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/{userId}")
	public ResponseEntity<ProfileResponseDto> getUserProfile(@PathVariable("userId") Integer userId) {
		return ResponseEntity.ok(userService.getUserProfile(userId));
	}
}