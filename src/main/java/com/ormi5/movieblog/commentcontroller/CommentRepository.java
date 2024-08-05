package com.ormi5.movieblog.commentcontroller;

import com.ormi5.movieblog.post.Post;
import com.ormi5.movieblog.comment.Comment;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
	List<Comment> findByPost(Post post);

	Comment findByPost_PostIdAndCommentId(Long postId, Long commentId);

}