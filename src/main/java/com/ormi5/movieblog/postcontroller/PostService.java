package com.ormi5.movieblog.postcontroller;

import com.ormi5.movieblog.post.PostDto;
import com.ormi5.movieblog.post.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PostService {
    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository){
        this.postRepository = postRepository;
    }

    public Optional<PostDto> getPostById(Long id) {
        return postRepository.findById(id)
                .map(PostDto::toPostDTO);
      
    public PostDto createPost(PostDto postDto) {
      Post post = PostDto.toEntity(postDto);

      Post savePost = postRepository.save(post);

      return PostDto.toDto(savePost);
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
