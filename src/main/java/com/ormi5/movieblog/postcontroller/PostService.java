package com.ormi5.movieblog.postcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PostService {
    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Transactional
    public boolean deletePost(int id) {
        return  postRepository.findById(id)
                .map(post -> {
                    postRepository.delete(post);
                    return true;
                })
                .orElse(false);
    }
}
