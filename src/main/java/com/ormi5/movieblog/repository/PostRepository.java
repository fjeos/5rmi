package com.ormi5.movieblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ormi5.movieblog.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}