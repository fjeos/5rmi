package com.ormi5.movieblog.commentcontroller;

import com.ormi5.movieblog.comment.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public void deleteComment(Long commentId, Long userId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("댓글을 찾을 수 없습니다"));

        if (!comment.getUserId().equals(userId)) {
            throw new RuntimeException("댓글을 삭제할 권한이 없습니다");
        }

        commentRepository.delete(comment);
    }
}
