package com.ormi5.movieblog.postcontroller;

import com.ormi5.movieblog.post.PostDto;
import com.ormi5.movieblog.post.Post;

import com.ormi5.movieblog.post.PostUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	@Transactional
	public PostDto createPost(PostDto postDto) {
		Post post = PostDto.toEntity(postDto);

		Post savePost = postRepository.save(post);

		return PostDto.toDto(savePost);
	}

	@Transactional
	public List<PostDto> getAllPosts() {
		List<Post> posts = postRepository.findAll();

		return posts.stream()
			.map(PostDto::toDto)
			.collect(Collectors.toList());
	}


	@Transactional(readOnly = true)
	public PostDto getPostById(Long id) {
		return postRepository.findById(id)
				.map(PostDto::toDto)
				.orElseThrow(() -> new IllegalArgumentException("글을 찾을 수 없습니다."));
	}

	@Transactional
	public List<PostDto> getPostsByUserId(Long userId) {
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
	public List<Post> getUserPosts(Long userId) {
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
	public void updatePost(Long postId, PostUpdateDto postDto) {
		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new IllegalArgumentException("해당 게시글을 찾을 수 없습니다."));

		post.updatePost(postDto);

	}

	@Transactional
	public boolean deletePost(Long id) {
		return postRepository.findById(id)
			.map(post -> {
				postRepository.delete(post);
				return true;
			})
			.orElse(false);
	}
}