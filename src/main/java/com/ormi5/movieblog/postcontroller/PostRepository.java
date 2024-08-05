package com.ormi5.movieblog.postcontroller;

import java.util.List;

import com.ormi5.movieblog.post.Post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ormi5.movieblog.user.User;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
	// UserId에 해당하는 유저가 작성한 모든 게시글을 가져오는 쿼리 메서드
	List<Post> findByUserId(Long userId);

	// 제목이 일치하는 모든 게시글을 가져오는 쿼리 메서드
	List<Post> findByTitle(String title);

	// 제목에 특정 키워드를 포함하는 모든 게시글을 대소문자를 구분하여 가져오는 쿼리 메서드
	List<Post> findByTitleContaining(String keyword); // 포함 여부 확인 키워드: Containing

	// 제목에 특정 키워드를 포함하는 모든 게시글을 대소문자 구분없이 가져오는 쿼리 메서드
	List<Post> findByTitleContainingIgnoreCase(String keyword); // IgnoreCase: 대소문자 구분 없이 조회하는 쿼리 메서드 키워드

	// 유저 최근 5개 게시글 조회 메서드
	List<Post> findTop5ByUserOrderByCreateAtDesc(User user);

	// 유저의 모든 게시글 조회 메서드
	List<Post> findAllByUserOrderByCreateAtDesc(User user);

}