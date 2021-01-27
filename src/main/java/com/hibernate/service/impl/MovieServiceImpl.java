package com.hibernate.service.impl;

import com.hibernate.dao.MovieDao;
import com.hibernate.lib.Inject;
import com.hibernate.lib.Service;
import com.hibernate.model.Movie;
import com.hibernate.service.MovieService;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {
    @Inject
    private MovieDao movieDao;

    @Override
    public Movie add(Movie movie) {
        return movieDao.add(movie);
    }

    @Override
    public List<Movie> getAll() {
        return movieDao.getAll();
    }
}
