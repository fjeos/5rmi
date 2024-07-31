package com.ormi5.movieblog.postcontroller;

import com.ormi5.movieblog.post.PostDto;
import com.ormi5.movieblog.post.Post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
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
			// .filter(post -> post.getIsShared())
			.map(PostDto::toDto)
			.toList();
	}

	@Transactional
	public Post updatePost(Long postId, PostDto postDto) {
		Post post = postRepository.findById(postId).orElse(null);

		if (post != null) {
			post.updatePost(postDto);
		}

		return postRepository.save(post); // 수정된 엔티티 저장
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