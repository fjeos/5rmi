package com.ormi5.movieblog.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.ormi5.movieblog.dto.PostDto;
import com.ormi5.movieblog.entity.Post;
import com.ormi5.movieblog.repository.PostRepository;

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