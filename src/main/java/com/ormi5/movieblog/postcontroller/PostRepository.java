package com.ormi5.movieblog.postcontroller;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ormi5.movieblog.post.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}