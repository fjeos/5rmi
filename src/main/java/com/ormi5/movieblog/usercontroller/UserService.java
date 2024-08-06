package com.ormi5.movieblog.usercontroller;

import com.ormi5.movieblog.announcement.Announcement;
import com.ormi5.movieblog.announcement.AnnouncementDto;
import com.ormi5.movieblog.announcementcontroller.AnnouncementController;
import com.ormi5.movieblog.announcementcontroller.AnnouncementRepository;
import com.ormi5.movieblog.announcementcontroller.AnnouncementService;
import com.ormi5.movieblog.post.Post;
import com.ormi5.movieblog.post.PostDto;

import com.ormi5.movieblog.post.ProfilePostResponseDto;
import com.ormi5.movieblog.postcontroller.PostService;
import com.ormi5.movieblog.user.ProfileResponseDto;

import com.ormi5.movieblog.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import com.ormi5.movieblog.user.UserDto;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.swing.text.html.Option;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final PostService postService;
	private final AnnouncementService announcementService;
	private final AnnouncementRepository announcementRepository;

	public UserService(UserRepository userRepository, PostService postService, PasswordEncoder passwordEncoder, AnnouncementService announcementService, AnnouncementRepository announcementRepository) {
		this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.postService = postService;
		this.announcementService = announcementService;
		this.announcementRepository = announcementRepository;
	}

	@Transactional
	public List<UserDto> getAllUsers() {
		return userRepository.findAll()
				.stream()
				.map(UserDto::fromEntity)
				.toList();
	}

	@Transactional
	public Optional<User> findByUserId(int userId) {
		return userRepository.findById(userId).map(user -> {return user;});
	}

	@Transactional
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Transactional
	public List<UserDto> getUsersByUsername(String keyword) {
		return userRepository.findByUsernameContaining(keyword)
				.stream()
				.map(UserDto::fromEntity)
				.toList();
	}

	@Transactional
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
				.op(false)
				.build();

		return userRepository.save(user);
	}


	/**
	 * 유저의 프로필 정보를 조회하는 메서드
	 * @param userId 프로필 정보를 가져올 유저
	 * @return 프로필 페이지에 나타낼 정보를 담은 Dto
	 */
	@Transactional
	public ProfileResponseDto getUserProfile(Integer userId) {

		User findUser = userRepository.findById(userId)
				.orElseThrow(() -> new IllegalArgumentException("회원 정보가 존재하지 않습니다."));

        List<Post> userPosts = postService.getUserPosts(userId);
		return ProfileResponseDto.builder()
				.level(findUser.getLevel())
				.username(findUser.getUsername())
				.reviewCount(userPosts.size())
				.signupDate(findUser.getSignupDate())
				.postList(userPosts.stream().map(ProfilePostResponseDto::toDto).toList())
				.build();
	}

	@Transactional
    public void updateAnnouncement(Integer id, AnnouncementDto updateAnnouncementDto) {
		Announcement announcement = announcementRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("해당 게시글을 찾을 수 없습니다."));

		announcement.updateAnnouncement(updateAnnouncementDto);
    }

	@Transactional
    public Optional<User> toggleStop(Integer id) {
		return userRepository.findById(id).map(target -> {
			target.updateStop(!target.getIsStop());
			return target;
		});
    }

	@Transactional
	public Optional<User> toggleOp(Integer id) {
		return userRepository.findById(id).map(target -> {
			target.updateOp(!target.getOp());
			return target;
		});
	}
}