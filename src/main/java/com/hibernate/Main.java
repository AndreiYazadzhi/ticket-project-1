package com.hibernate;

import com.hibernate.lib.Injector;
import com.hibernate.model.Movie;
import com.hibernate.model.MovieSession;
import com.hibernate.model.User;
import com.hibernate.service.AuthenticationService;
import com.hibernate.service.MovieService;
import com.hibernate.service.MovieSessionService;
import com.hibernate.service.ShoppingCardService;

public class Main {
    private static Injector injector = Injector.getInstance("com.hibernate");
    private static final AuthenticationService authService =
            (AuthenticationService) injector.getInstance(AuthenticationService.class);
    private static final ShoppingCardService shoppingCardService =
            (ShoppingCardService) injector.getInstance(ShoppingCardService.class);
    private static final MovieSessionService movieSessionService =
            (MovieSessionService) injector.getInstance(MovieSessionService.class);
    private static final MovieService movieService =
            (MovieService) injector.getInstance(MovieService.class);
    private static final User user = authService.register("123", "123");

    public static void main(String[] args) {
        MovieSession movieSession = new MovieSession();
        Movie movie = new Movie();
        movie.setTitle("Moana");
        movieSession.setMovie(movie);
        movieService.add(movie);
        movieSessionService.add(movieSession);
        shoppingCardService.addSession(movieSession, user);
        shoppingCardService.clear(shoppingCardService.getByUser(user));
        System.out.println(shoppingCardService.getByUser(user));
    }
}
