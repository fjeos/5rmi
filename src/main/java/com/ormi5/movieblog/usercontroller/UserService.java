package com.ormi5.movieblog.usercontroller;

import com.ormi5.movieblog.user.User;
import com.ormi5.movieblog.user.UserDto;
import com.ormi5.movieblog.usercontroller.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
@Transactional(readOnly = true)
public class UserService {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Transactional
	public User save(UserDto userDto) {
		User user = userDto.toEntity();
		return userRepository.save(user);
	}

	@Transactional
	public User addUser(UserDto userDto) {
		User user = User.builder()
				.email(userDto.getEmail())
				.username(userDto.getUsername())
				.password(passwordEncoder.encode(userDto.getPassword()))
				.level(1)
				.signupDate(Instant.now())
				.isStop(false)
				.build();

		return userRepository.save(user);
	}
}