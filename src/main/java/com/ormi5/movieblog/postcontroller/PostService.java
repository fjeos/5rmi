package com.ormi5.movieblog.postcontroller;

import org.springframework.stereotype.Service;

import com.ormi5.movieblog.post.PostDto;
import com.ormi5.movieblog.post.Post;

@Service
public class PostService {
	private final PostRepository postRepository;

	public PostService(PostRepository postRepository) {
		this.postRepository = postRepository;
	}

	public PostDto createPost(PostDto postDto) {
		Post post = PostDto.toEntity(postDto);

		Post savePost = postRepository.save(post);

		return PostDto.toDto(savePost);
	}
}