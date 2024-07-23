package com.ormi5.movieblog.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ormi5.movieblog.dto.PostDto;
import com.ormi5.movieblog.entity.Post;
import com.ormi5.movieblog.repository.PostRepository;

@Service
public class PostService {
	private PostRepository postRepository;

	// List<Post> posts = new ArrayList<>(); // Posts 를 담을 리스트
	// private Long nextPostId = 1L; // Posts id 값

	public PostService(PostRepository postRepository) {
		this.postRepository = postRepository;
	}

	public PostDto createPost(PostDto postDto) {
		Post post = convertToPostEntity(postDto);

		postRepository.save(post);

		return convertToPostDto(post);
	}

	private static Post convertToPostEntity(PostDto postDto) {
		Post post = new Post();

		post.setUserId(postDto.getUserId());
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setAuthor(postDto.getAuthor());
		post.setCreatedAt(postDto.getCreatedAt());

		return post;
	}

	private static PostDto convertToPostDto(Post post) {
		PostDto postDto = new PostDto();

		postDto.setUserId(post.getUserId());
		postDto.setTitle(post.getTitle());
		postDto.setContent(post.getContent());
		postDto.setAuthor(post.getAuthor());
		postDto.setCreatedAt(post.getCreatedAt());

		return postDto;
	}
}