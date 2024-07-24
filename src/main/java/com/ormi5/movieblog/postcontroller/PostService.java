package com.ormi5.movieblog.postcontroller;

import org.springframework.stereotype.Service;

import com.ormi5.movieblog.post.PostDTO;
import com.ormi5.movieblog.post.Post;

@Service
public class PostService {
	private final PostRepository postRepository;

	public PostService(PostRepository postRepository) {
		this.postRepository = postRepository;
	}

	public PostDTO createPost(PostDTO postDTO) {
		Post post = PostDTO.toEntity(postDTO);

		Post savePost = postRepository.save(post);

		return PostDTO.toDTO(savePost);
	}
}