package com.ormi5.movieblog.commentcontroller;

import com.ormi5.movieblog.post.Post;
import com.ormi5.movieblog.comment.Comment;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
	List<Comment> findByPost(Post post);

	Comment findByPost_PostIdAndId(Integer postId, Integer commentId);

}