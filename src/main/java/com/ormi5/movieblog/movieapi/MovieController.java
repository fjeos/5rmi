package com.ormi5.movieblog.movieapi;

import com.ormi5.movieblog.movie.MovieDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movie")
public class MovieController {
    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    /**
     * DB 에 저장 되어 있는 모든 영화 조회: 게시글의 ID를 받아 해당 게시글 조회
     *
     * @author junhyun
     * @return 조회된 모든 영화 정보, 없다면 Optional.empty
     */
    @GetMapping("/list")
    public ResponseEntity<List<MovieDto>> getAllMovies() {
        List<MovieDto> movieDtos = movieService.getAllMovies();
        return ResponseEntity.ok(movieDtos);
    }

    /**
     * 특정 영화 조회: 게시글의 이름를 받아 해당 영화 조회
     *
     * @param title 조회할 영화의 제목
     * @return 조회된 영화 정보, 없다면 Optional.empty
     * @author junhyun
     */
    @GetMapping("/{title}")
    public ResponseEntity<Optional<MovieDto>> getMovieByTitle(@PathVariable("title") String title) {
        Optional<MovieDto> movie = movieService.getMovieByTitle(title);

        System.out.println(movie.get().getTitle());
        return ResponseEntity.ok(movie);
    }
}
