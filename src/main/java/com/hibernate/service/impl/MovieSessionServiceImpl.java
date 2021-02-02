package com.hibernate.service.impl;

import com.hibernate.dao.MovieSessionDao;
import com.hibernate.lib.Inject;
import com.hibernate.lib.Service;
import com.hibernate.model.MovieSession;
import com.hibernate.service.MovieSessionService;
import java.time.LocalDate;
import java.util.List;

@Service
public class MovieSessionServiceImpl implements MovieSessionService {
    @Inject
    private MovieSessionDao movieSessionDao;

    @Override
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate date) {
        return movieSessionDao.findAvailableSessions(movieId, date);
    }

    @Override
    public MovieSession add(MovieSession session) {
        return movieSessionDao.add(session);
    }
}
