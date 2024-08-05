package com.ormi5.movieblog.movie;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 64)
    private String name;

    @Column(name = "create_dts", length = 4)
    private String createDts;

    @Column(name = "create_dte", length = 4)
    private String createDte;

    @Column(name = "release_dts", length = 8)
    private String releaseDts;

    @Column(name = "release_dte", length = 8)
    private String releaseDte;

    @Column(name = "nation", length = 64)
    private String nation;

    @Column(name = "company", length = 64)
    private String company;

    @Column(name = "genre", nullable = false, length = 32)
    private String genre;

    @Column(name = "rated_yn", length = 8)
    private String ratedYn;

    @Column(name = "`use`", length = 32)
    private String use;

    @Column(name = "movie_id", nullable = false, length = 32)
    private String movieId;

    @Column(name = "movie_seq", length = 32)
    private String movieSeq;

    @Column(name = "type", length = 32)
    private String type;

    @Column(name = "title", length = 64)
    private String title;

    @Column(name = "director", length = 32)
    private String director;

    @Column(name = "actor", length = 128)
    private String actor;

    @Column(name = "staff", length = 256)
    private String staff;

    @Column(name = "keyword", length = 256)
    private String keyword;

    @Column(name = "plot", length = 256)
    private String plot;

}