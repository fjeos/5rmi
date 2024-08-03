package com.ormi5.movieblog.movieapi;

import com.ormi5.movieblog.movie.Movie;
import com.ormi5.movieblog.movie.MovieDto;
import com.ormi5.movieblog.post.Post;
import com.ormi5.movieblog.post.PostDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieService {
    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Transactional
    public MovieDto createMovie(MovieDto movieDto) {
        Movie movie = movieDto.toEntity();

        Movie saveMovie = movieRepository.save(movie);

        return MovieDto.of(saveMovie);
    }

    @Transactional
    public List<MovieDto> getAllMovies() {
        List<Movie> movies = movieRepository.findAll();

        return movies.stream()
                .map(MovieDto::of)
                .collect(Collectors.toList());
    }

    @Transactional
    public Optional<MovieDto> getMovieById(Long id) {
        return movieRepository.findById(id)
                .map(MovieDto::of);
    }

    @Transactional
    public List<MovieDto> getMoviesByUserId(Long userId) {
        return movieRepository.findByUserId(userId) // UserID에 해당하는 유저의
                .stream()
                .map(MovieDto::of)
                .toList();
    }

    @Transactional
    public Movie updateMovie(Long postId, MovieDto movieDto) {
        Movie movie = movieRepository.findById(postId).orElse(null);

        if (movie != null) {
            movie.update(movieDto);
        }

        return movieRepository.save(movie); // 수정된 엔티티 저장
    }

    @Transactional
    public boolean deleteMovie(Long id) {
        return movieRepository.findById(id)
                .map(movie -> {
                    movieRepository.delete(movie);
                    return true;
                })
                .orElse(false);
    }

    @Transactional
    public Optional<MovieDto> getMovieByTitle(String title) {
        return movieRepository.findByTitle(title)
                .map(MovieDto::of);
    }
}
