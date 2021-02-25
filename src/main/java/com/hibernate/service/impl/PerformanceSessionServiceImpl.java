package com.hibernate.service.impl;

import com.hibernate.dao.PerformanceSessionDao;
import com.hibernate.model.PerformanceSession;
import com.hibernate.service.PerformanceSessionService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PerformanceSessionServiceImpl implements PerformanceSessionService {
    private final PerformanceSessionDao performanceSessionDao;

    @Autowired
    public PerformanceSessionServiceImpl(PerformanceSessionDao performanceSessionDao) {
        this.performanceSessionDao = performanceSessionDao;
    }

    @Override
    public List<PerformanceSession> findAvailableSessions(Long movieId, LocalDate date) {
        return performanceSessionDao.findAvailableSessions(movieId, date);
    }

    @Override
    public List<PerformanceSession> getAll() {
        return performanceSessionDao.getAll();
    }

    @Override
    public void update(PerformanceSession performanceSession) {
        performanceSessionDao.update(performanceSession);
    }

    @Override
    public void remove(Long id) {
        performanceSessionDao.remove(id);
    }

    @Override
    public PerformanceSession get(Long id) {
        return performanceSessionDao.get(id).get();
    }

    @Override
    public PerformanceSession add(PerformanceSession session) {
        return performanceSessionDao.add(session);
    }
}
