package com.ormi5.movieblog.postcontroller;

import com.ormi5.movieblog.post.PostDto;
import com.ormi5.movieblog.post.Post;
import com.ormi5.movieblog.post.PostResponseDto;
import com.ormi5.movieblog.user.UserDto;
import com.ormi5.movieblog.user.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class PostService {
	private final PostRepository postRepository;

	@Autowired
	public PostService(PostRepository postRepository) {
		this.postRepository = postRepository;
	}

	/*@Transactional
	public PostDto createPost(PostDto postDto) {
		Post post = Post.builder()
				.user(postDto.getUser().toEntity())
				.title(postDto.getTitle())
				.content(postDto.getContent())
				.isShared(postDto.getIsShared())
				.likesCount(0)
				.createAt(Instant.now())
				.updateAt(Instant.now())
				.movieId(null) // TODO Implement movie dto
				.build();

		return PostDto.toDto(postRepository.save(post));
	}*/

	@Transactional
	public void createPost(PostDto postDto, UserDto userDto) {
		Post post = Post.builder()
				.user(userDto.toEntity())
				.title(postDto.getTitle())
				.content(postDto.getContent())
				.isShared(postDto.getIsShared())
				.likesCount(0)
				.createAt(Instant.now())
				.movieId(null) // TODO Implement movie dto
				.build();

		postRepository.save(post);
	}

	@Transactional
	public List<PostDto> getAllPosts() {
		List<Post> posts = postRepository.findAll();

		return posts.stream()
			.map(PostDto::toDto)
			.collect(Collectors.toList());
	}

	@Transactional
	public List<PostDto> getSharedPosts() {
		List<Post> posts = postRepository.findByIsSharedTrue();

		return posts.stream()
				.map(PostDto::toDto)
				.collect(Collectors.toList());
	}


	@Transactional(readOnly = true)
	public PostDto getPostById(Integer id) {
		return postRepository.findById(id)
				.map(PostDto::toDto)
				.orElseThrow(() -> new IllegalArgumentException("글을 찾을 수 없습니다."));
	}

	@Transactional
	public List<PostDto> getPostsByUserId(Integer userId) {
		return postRepository.findByUserId(userId) // UserID에 해당하는 유저의
			.stream()
			.filter(Post::getIsShared) // 공개 상태의 게시글만 조회
			.map(PostDto::toDto)
			.toList();
	}

	/**
	 * 공개 여부와 관계없이 userId의 유저가 작성한 모든 게시글을 조회하는 메서드
	 * @param userId 작성자
	 * @return userId의 유저가 작성한 게시글 List
	 */
	@Transactional
	public List<Post> getUserPosts(Integer userId) {
		return postRepository.findByUserId(userId);
	}

	@Transactional
	public List<PostDto> getPostsByTitle(String title) {
		return postRepository.findByTitle(title)
			.stream()
			.filter(Post::getIsShared)
			.map(PostDto::toDto)
			.toList();
	}

	@Transactional
	public List<PostDto> getPostsByContainingTitleCaseSensitive(String keyword) {
		return postRepository.findByTitleContaining(keyword)
			.stream()
			.filter(post -> post.getIsShared() && post.getTitle().contains(keyword))
			.map(PostDto::toDto)
			.collect(Collectors.toList());
	}

	@Transactional
	public List<PostDto> getPostsByContainingTitleCaseInsensitive(String keyword) {
		return postRepository.findByTitleContainingIgnoreCase(keyword)
			.stream()
			.filter(Post::getIsShared)
			.map(PostDto::toDto)
			.collect(Collectors.toList());
	}

	@Transactional
	public void updatePost(Integer postId, PostResponseDto postDto) {
		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new IllegalArgumentException("해당 게시글을 찾을 수 없습니다."));

		post.updatePost(postDto);
	}

	@Transactional
	public boolean deletePost(Integer id) {
		return postRepository.findById(id)
			.map(post -> {
				postRepository.delete(post);
				return true;
			})
			.orElse(false);
	}


	//사용자 최근게시글 조회
	@Transactional(readOnly = true)
	public List<PostDto> getRecentPosts(User user, int limit) {
		return postRepository.findTop5ByUserOrderByCreateAtDesc(user).stream()
				.limit(limit)
				.map(PostDto::toDto)
				.collect(Collectors.toList());
	}

	//사용자 모든게시글 조회
	@Transactional(readOnly = true)
	public List<PostDto> getAllPosts(User user) {
		return postRepository.findAllByUserOrderByCreateAtDesc(user).stream()
				.map(PostDto::toDto)
				.collect(Collectors.toList());
	}

	@Transactional
	public List<PostDto> getPostByKeyword(String searchKeyword){
		return postRepository.findByTitleContaining(searchKeyword)
				.stream()
				.filter(Post::getIsShared)
				.map(PostDto::toDto)
				.toList();
	}

	public List<PostDto> getPostsByMovieName(String keyword) {
		return postRepository.findByMovieNameContaining(keyword)
				.stream()
				.filter(Post::getIsShared)
				.map(PostDto::toDto)
				.toList();
	}


	@Transactional
	public void increaseLike(Integer postId, Model model) {
		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));
		post.increaseLike();
	}
}

