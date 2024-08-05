package com.ormi5.movieblog.postcontroller;

import java.util.List;

import com.ormi5.movieblog.post.Post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
	// UserId에 해당하는 유저가 작성한 모든 게시글을 가져오는 쿼리 메서드
	List<Post> findByUserId(Integer userId);

	@Query("SELECT p FROM Post p WHERE p.movieId.name LIKE %:keyword%")
	List<Post> findByMovieNameContaining(@Param("keyword") String keyword);

	// 제목이 일치하는 모든 게시글을 가져오는 쿼리 메서드
	List<Post> findByTitle(String title);

	// 제목에 특정 키워드를 포함하는 모든 게시글을 대소문자를 구분하여 가져오는 쿼리 메서드
	List<Post> findByTitleContaining(String keyword); // 포함 여부 확인 키워드: Containing

	// 제목에 특정 키워드를 포함하는 모든 게시글을 대소문자 구분없이 가져오는 쿼리 메서드
	List<Post> findByTitleContainingIgnoreCase(String keyword); // IgnoreCase: 대소문자 구분 없이 조회하는 쿼리 메서드 키워드
}