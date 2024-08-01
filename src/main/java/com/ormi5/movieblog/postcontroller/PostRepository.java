package com.ormi5.movieblog.postcontroller;

import java.util.List;

import com.ormi5.movieblog.post.Post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
	// UserId에 해당하는 유저가 작성한 모든 게시글을 가져오는 쿼리 메서드
	List<Post> findByUserId(Long userId);
}