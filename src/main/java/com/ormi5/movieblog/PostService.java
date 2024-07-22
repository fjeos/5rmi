package com.ormi5.movieblog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PostService {
    private PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository){
        this.postRepository = postRepository;
    }

    public Optional<PostDTO> getPostById(Long id) {
        return postRepository.findById(id)
                .map(PostDTO::toPostDTO);
    }
}
