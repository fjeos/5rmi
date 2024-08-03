package com.ormi5.movieblog.movie;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link Movie}
 */
@Value
@AllArgsConstructor
@Builder
public class MovieDto implements Serializable {
    Integer id;
    String name;
    String createDts;
    String createDte;
    String releaseDts;
    String releaseDte;
    String nation;
    String company;
    String genre;
    String ratedYn;
    String use;
    String movieId;
    String movieSeq;
    String type;
    String title;
    String director;
    String actor;
    String staff;
    String keyword;
    String plot;

    public static MovieDto of(Movie movie) {
        return MovieDto.builder()
                .id(movie.getId())
                .name(movie.getName())
                .createDts(movie.getCreateDts())
                .createDte(movie.getCreateDte())
                .releaseDts(movie.getReleaseDts())
                .releaseDte(movie.getReleaseDte())
                .nation(movie.getNation())
                .company(movie.getCompany())
                .genre(movie.getGenre())
                .ratedYn(movie.getRatedYn())
                .use(movie.getUse())
                .movieId(movie.getMovieId())
                .movieSeq(movie.getMovieId())
                .type(movie.getType())
                .title(movie.getTitle())
                .director(movie.getDirector())
                .actor(movie.getActor())
                .staff(movie.getStaff())
                .keyword(movie.getKeyword())
                .plot(movie.getPlot()).build();
    }

    public Movie toEntity() {
        return Movie.builder()
                .id(this.getId())
                .name(this.getName())
                .createDts(this.getCreateDts())
                .createDte(this.getCreateDte())
                .releaseDts(this.getReleaseDts())
                .releaseDte(this.getReleaseDte())
                .nation(this.getNation())
                .company(this.getCompany())
                .genre(this.getGenre())
                .ratedYn(this.getRatedYn())
                .use(this.getUse())
                .movieId(this.getMovieId())
                .movieSeq(this.getMovieId())
                .type(this.getType())
                .title(this.getTitle())
                .director(this.getDirector())
                .actor(this.getActor())
                .staff(this.getStaff())
                .keyword(this.getKeyword())
                .plot(this.getPlot()).build();
    }
}