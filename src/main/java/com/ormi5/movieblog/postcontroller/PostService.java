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

		return PostDto.toDTO(savePost);
	}

	@Transactional
	public List<PostDto> getAllPosts() {
		List<Post> posts = postRepository.findAll();

		return posts.stream()
			.map(PostDto::toDTO)
			.collect(Collectors.toList());
	}

	public Optional<PostDto> getPostById(int id) {
		return postRepository.findById(id)
			.map(PostDto::toDTO);
	}

	@Transactional
	public Post updatePost(int postId, PostDto postDto) {
		Post post = postRepository.findById(postId).orElse(null);

		if (post != null) {
			post.updatePost(postDto);
		}

		return null;
	}

	@Transactional
	public boolean deletePost(int id) {
		return postRepository.findById(id)
			.map(post -> {
				postRepository.delete(post);
				return true;
			})
			.orElse(false);
	}

}