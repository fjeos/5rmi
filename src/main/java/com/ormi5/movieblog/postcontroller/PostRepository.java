package com.ormi5.movieblog.postcontroller;

import com.ormi5.movieblog.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface PostRepository extends JpaRepository<Post, Integer> {
}
