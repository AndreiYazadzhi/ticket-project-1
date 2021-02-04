package com.hibernate;

import com.hibernate.lib.Injector;
import com.hibernate.model.Movie;
import com.hibernate.model.MovieSession;
import com.hibernate.model.User;
import com.hibernate.service.AuthenticationService;
import com.hibernate.service.MovieService;
import com.hibernate.service.MovieSessionService;
import com.hibernate.service.ShoppingCartService;

public class Main {
    private static Injector injector = Injector.getInstance("com.hibernate");
    private static final AuthenticationService authService =
            (AuthenticationService) injector.getInstance(AuthenticationService.class);
    private static final ShoppingCartService SHOPPING_CART_SERVICE =
            (ShoppingCartService) injector.getInstance(ShoppingCartService.class);
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
        SHOPPING_CART_SERVICE.addSession(movieSession, user);
        SHOPPING_CART_SERVICE.clear(SHOPPING_CART_SERVICE.getByUser(user));
        System.out.println(SHOPPING_CART_SERVICE.getByUser(user));
    }
}
