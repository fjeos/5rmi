package com.ormi5.movieblog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Transactional
    public Post updatePost(Long postId, PostUpdateDto postUpdateDto) {
        Post post = postRepository.findById(postId).orElse(null);
        if (post != null) {
            if (postUpdateDto.getTitle() != null) {
                post.setTitle(postUpdateDto.getTitle());
            }
            if (postUpdateDto.getContent() != null) {
                post.setContent(postUpdateDto.getContent());
            }
            if (postUpdateDto.getIsShared() != null) {
                post.setIsShared(postUpdateDto.getIsShared());
            }
            post.setUpdateAt(LocalDateTime.now());
            return postRepository.save(post);
        }
        return null;
    }
}
