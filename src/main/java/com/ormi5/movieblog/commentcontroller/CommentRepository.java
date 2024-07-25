package com.ormi5.movieblog.commentcontroller;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ormi5.movieblog.comment.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}