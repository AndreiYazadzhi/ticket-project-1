package com.hibernate.service.impl;

import com.hibernate.dao.CinemaHallDao;
import com.hibernate.lib.Inject;
import com.hibernate.lib.Service;
import com.hibernate.model.CinemaHall;
import com.hibernate.service.CinemaHallService;
import java.util.List;

@Service
public class CinemaHallServiceImpl implements CinemaHallService {
    @Inject
    private CinemaHallDao cinemaHallDao;

    @Override
    public CinemaHall add(CinemaHall cinemaHall) {
        return cinemaHallDao.add(cinemaHall);
    }

    @Override
    public List<CinemaHall> getAll() {
        return cinemaHallDao.getAll();
    }
}
