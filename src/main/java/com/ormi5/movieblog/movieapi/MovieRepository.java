package com.ormi5.movieblog.movieapi;

import com.ormi5.movieblog.movie.Movie;
import com.ormi5.movieblog.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findByUserId(Long userId);

    Optional<Movie> findByTitle(String title);
}
