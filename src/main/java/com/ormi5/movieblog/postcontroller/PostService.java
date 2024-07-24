package com.ormi5.movieblog.postcontroller;

import com.ormi5.movieblog.post.Post;
import com.ormi5.movieblog.post.PostDto;
import com.ormi5.movieblog.post.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import java.lang.reflect.Field;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Transactional
    public PostDto createBoardPost(PostDto postDto) {
        Post post = createPostFromDto(postDto);
        Post savedPost = postRepository.save(post);
        return convertToDto(savedPost);
    }

    @Transactional
    public List<PostDto> getAllBoardPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public PostDto getBoardPostById(Integer id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다.: " + id));
        return convertToDto(post);
    }

    private Post createPostFromDto(PostDto postDto) {
        Post post = new Post();
        try {
            Field userIdField = Post.class.getDeclaredField("userId");
            userIdField.setAccessible(true);
            userIdField.set(post, postDto.getUserId());

            Field authorField = Post.class.getDeclaredField("author");
            authorField.setAccessible(true);
            authorField.set(post, postDto.getAuthor());

            Field titleField = Post.class.getDeclaredField("title");
            titleField.setAccessible(true);
            titleField.set(post, postDto.getTitle());

            Field contentField = Post.class.getDeclaredField("content");
            contentField.setAccessible(true);
            contentField.set(post, postDto.getContent());

            Field isSharedField = Post.class.getDeclaredField("isShared");
            isSharedField.setAccessible(true);
            isSharedField.set(post, postDto.getIsShared());

            Field likesCountField = Post.class.getDeclaredField("likesCount");
            likesCountField.setAccessible(true);
            likesCountField.set(post, postDto.getLikesCount());

            Field createAtField = Post.class.getDeclaredField("createAt");
            createAtField.setAccessible(true);
            createAtField.set(post, Instant.now());

            Field updateAtField = Post.class.getDeclaredField("updateAt");
            updateAtField.setAccessible(true);
            updateAtField.set(post, Instant.now());
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return post;
    }

    private PostDto convertToDto(Post post) {
        return new PostDto(
                post.getPostId(),
                post.getUserId(),
                post.getAuthor(),
                post.getTitle(),
                post.getContent(),
                post.getIsShared(),
                post.getLikesCount(),
                post.getCreateAt(),
                post.getUpdateAt()
        );
    }
}
