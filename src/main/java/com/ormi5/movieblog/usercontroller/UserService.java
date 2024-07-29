package com.ormi5.movieblog.usercontroller;

import com.ormi5.movieblog.post.Post;
import com.ormi5.movieblog.post.ProfilePostResponseDto;
import com.ormi5.movieblog.postcontroller.PostService;
import com.ormi5.movieblog.user.ProfileResponseDto;
import com.ormi5.movieblog.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserService {
	private final UserRepository userRepository;
	private final PostService postService;

	@Autowired
	public UserService(UserRepository userRepository, PostService postService) {
		this.userRepository = userRepository;
		this.postService = postService;
	}


	/**
	 * 유저의 프로필 정보를 조회하는 메서드
	 * @param userId 프로필 정보를 가져올 유저
	 * @return 프로필 페이지에 나타낼 정보를 담은 Dto
	 */
	public ProfileResponseDto getUserProfile(Long userId) {

		User findUser = userRepository.findById(userId)
				.orElseThrow(() -> new IllegalArgumentException("회원 정보가 존재하지 않습니다."));

        List<Post> userPosts = postService.getUserPosts(userId);

		return ProfileResponseDto.builder()
				//.level(findUser.getLevel())
				//.username(findUser.getUsername())
				.reviewCount(userPosts.size())
				//.signUp(findUser.getSingUpDate())
				.postList(userPosts.stream().map(ProfilePostResponseDto::toDto).toList())
				.build();
	}
}