package com.hibernate.service.impl;

import com.hibernate.dao.MovieSessionDao;
import com.hibernate.model.MovieSession;
import com.hibernate.service.MovieSessionService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieSessionServiceImpl implements MovieSessionService {
    private final MovieSessionDao movieSessionDao;

    @Autowired
    public MovieSessionServiceImpl(MovieSessionDao movieSessionDao) {
        this.movieSessionDao = movieSessionDao;
    }

    @Override
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate date) {
        return movieSessionDao.findAvailableSessions(movieId, date);
    }

    @Override
    public List<MovieSession> getAll() {
        return movieSessionDao.getAll();
    }

    @Override
    public void update(Long id) {
        movieSessionDao.update(id);
    }

    @Override
    public void remove(Long id) {
        movieSessionDao.remove(id);
    }

    @Override
    public MovieSession get(Long id) {
        return movieSessionDao.get(id).get();
    }

    @Override
    public MovieSession add(MovieSession session) {
        return movieSessionDao.add(session);
    }
}
