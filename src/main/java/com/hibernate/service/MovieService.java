package com.hibernate.service;

import com.hibernate.model.Movie;

import java.util.List;

public interface MovieService {
    Movie add(Movie movie);

    List<Movie> getAll();
}
