package com.ormi5.movieblog.postcontroller;

import com.ormi5.movieblog.post.PostDto;
import com.ormi5.movieblog.post.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.lang.reflect.Field;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class PostService {
    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository){
        this.postRepository = postRepository;
    }
  
    @Transactional
    public List<PostDto> getAllBoardPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
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
      
//      private Post createPostFromDto(PostDto postDto) {
//         Post post = new Post();
//         try {
//             Field userIdField = Post.class.getDeclaredField("userId");
//             userIdField.setAccessible(true);
//             userIdField.set(post, postDto.getUserId());

//             Field authorField = Post.class.getDeclaredField("author");
//             authorField.setAccessible(true);
//             authorField.set(post, postDto.getAuthor());

//             Field titleField = Post.class.getDeclaredField("title");
//             titleField.setAccessible(true);
//             titleField.set(post, postDto.getTitle());

//             Field contentField = Post.class.getDeclaredField("content");
//             contentField.setAccessible(true);
//             contentField.set(post, postDto.getContent());

//             Field isSharedField = Post.class.getDeclaredField("isShared");
//             isSharedField.setAccessible(true);
//             isSharedField.set(post, postDto.getIsShared());

//             Field likesCountField = Post.class.getDeclaredField("likesCount");
//             likesCountField.setAccessible(true);
//             likesCountField.set(post, postDto.getLikesCount());

//             Field createAtField = Post.class.getDeclaredField("createAt");
//             createAtField.setAccessible(true);
//             createAtField.set(post, Instant.now());

//             Field updateAtField = Post.class.getDeclaredField("updateAt");
//             updateAtField.setAccessible(true);
//             updateAtField.set(post, Instant.now());
//         } catch (NoSuchFieldException | IllegalAccessException e) {
//             throw new RuntimeException(e);
//         }
//         return post;
}
