package com.ormi5.movieblog.postcontroller;

import com.ormi5.movieblog.comment.Comment;
import com.ormi5.movieblog.comment.CommentDto;
import com.ormi5.movieblog.post.Post;
import com.ormi5.movieblog.post.PostDto;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {
    private final List<Post> boardPosts = new ArrayList<>();
    private Long nextPostId = 1L;

    public PostDto createBoardPost(PostDto postDto) {
        Post boardPost = convertToBoardPostEntity(postDto);
        boardPost.setId(nextPostId++);
        boardPost.setCreatedAt(LocalDateTime.now());
        boardPosts.add(boardPost);
        return convertToBoardPostDto(boardPost);
    }

    public List<PostDto> getAllBoardPosts() {
        return boardPosts.stream()
                .map(this::convertToBoardPostDto)
                .collect(Collectors.toList());
    }

    public PostDto getBoardPostById(Long id) {
        return boardPosts.stream()
                .filter(post -> post.getId().equals(id))
                .map(this::convertToBoardPostDto)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다.: " + id));
    }

    private PostDto convertToBoardPostDto(Post boardPost) {
        PostDto postDto = new PostDto();
        postDto.setId(boardPost.getId());
        postDto.setTitle(boardPost.getTitle());
        postDto.setContent(boardPost.getContent());
        postDto.setAuthor(boardPost.getAuthor());
        postDto.setCreatedAt(boardPost.getCreatedAt());
        postDto.setUpdatedAt(boardPost.getUpdatedAt());
        if (boardPost.getComments() != null) {
            postDto.setComments(
                    boardPost.getComments().stream()
                            .map(this::convertToCommentDto)
                            .collect(Collectors.toList())
            );
        }
        return postDto;
    }

    private Post convertToBoardPostEntity(PostDto postDto) {
        Post boardPost = new Post();
        boardPost.setTitle(postDto.getTitle());
        boardPost.setContent(postDto.getContent());
        boardPost.setAuthor(postDto.getAuthor());
        if (postDto.getComments() != null) {
            postDto.getComments().forEach(commentDto -> {
                Comment comment = convertToCommentEntity(commentDto);
                comment.setBoardPost(boardPost);
                boardPost.addComment(comment);
            });
        }
        return boardPost;
    }

    private CommentDto convertToCommentDto(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setContent(comment.getContent());
        commentDto.setAuthor(comment.getAuthor());
        commentDto.setCreatedAt(comment.getCreatedAt());
        return commentDto;
    }

    private Comment convertToCommentEntity(CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setContent(commentDto.getContent());
        comment.setAuthor(commentDto.getAuthor());
        comment.setCreatedAt(commentDto.getCreatedAt());
        return comment;
    }
}