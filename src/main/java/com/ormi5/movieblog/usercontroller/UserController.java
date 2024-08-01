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

@RestController
@RequestMapping("/user")
public class UserController {

}