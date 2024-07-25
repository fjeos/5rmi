package com.ormi5.movieblog.commentcontroller;

import com.ormi5.movieblog.comment.Comment;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}