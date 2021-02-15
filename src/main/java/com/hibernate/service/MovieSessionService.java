package com.hibernate.service;

import com.hibernate.model.MovieSession;
import java.time.LocalDate;
import java.util.List;

public interface MovieSessionService {
    List<MovieSession> findAvailableSessions(Long movieId, LocalDate date);

    List<MovieSession> getAll();

    void update(MovieSession movieSession);

    void remove(Long id);

    MovieSession get(Long id);

    MovieSession add(MovieSession session);
}
