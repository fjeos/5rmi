package com.ormi5.movieblog.movie;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link Movie}
 */
@Value
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
}