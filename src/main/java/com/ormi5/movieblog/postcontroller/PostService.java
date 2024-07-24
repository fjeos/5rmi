package com.ormi5.movieblog.postcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ormi5.movieblog.post.Post;
import com.ormi5.movieblog.post.PostDto;

import java.time.LocalDateTime;

@Service
public class PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Transactional
    public Post updatePost(Long postId, PostDto postDto) {
        Post post = postRepository.findById(postId).orElse(null);
        if (post != null) {
            if (postDto.getTitle() != null) {
                post.setTitle(postDto.getTitle());
            }
            if (postDto.getContent() != null) {
                post.setContent(postDto.getContent());
            }
            if (postDto.getIsShared() != null) {
                post.setIsShared(postDto.getIsShared());
            }
            post.setUpdateAt(LocalDateTime.now());
            return postRepository.save(post);
        }
        return null;
    }
}
