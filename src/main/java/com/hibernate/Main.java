package com.hibernate;

import com.hibernate.lib.Injector;
import com.hibernate.model.Movie;
import com.hibernate.service.MovieService;

public class Main {
    private static Injector injector = Injector.getInstance("com.hibernate");

    public static void main(String[] args) {
        Movie movie = new Movie();
        movie.setTitle("Fast and Furious");
        MovieService movieService = (MovieService) injector.getInstance(MovieService.class);
        movieService.add(movie);
        movieService.getAll().forEach(System.out::println);
    }
}
