package com.ormi5.movieblog.usercontroller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
	private final UserService UserService;

	@Autowired
	public UserController(UserService UserService) {
		this.UserService = UserService;
	}
}