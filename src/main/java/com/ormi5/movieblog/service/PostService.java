package com.ormi5.movieblog.service;

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
		Post post = convertToPostEntity(postDto);

		postRepository.save(post);

		return convertToPostDto(post);
	}

	private static Post convertToPostEntity(PostDto postDto) {
		return Post.builder()
			.postId(postDto.getPostId())
			.userId(postDto.getUserId())
			.author(postDto.getAuthor())
			.title(postDto.getTitle())
			.content(postDto.getContent())
			.isShared(postDto.getIsShared())
			.createAt(postDto.getCreateAt())
			.updateAt(postDto.getUpdateAt())
			.build();
	}

	private static PostDto convertToPostDto(Post post) {
		return PostDto.builder()
			.postId(post.getPostId())
			.userId(post.getUserId())
			.author(post.getAuthor())
			.title(post.getTitle())
			.content(post.getContent())
			.isShared(post.getIsShared())
			.createAt(post.getCreateAt())
			.updateAt(post.getUpdateAt())
			.build();
	}
}