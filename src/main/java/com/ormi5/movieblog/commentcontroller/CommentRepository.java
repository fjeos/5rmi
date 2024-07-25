package com.ormi5.movieblog.commentcontroller;

import java.util.List;

import com.ormi5.movieblog.comment.Comment;

import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ormi5.movieblog.comment.Comment;
import com.ormi5.movieblog.post.Post;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
	List<Comment> findByPost(Post post);
}