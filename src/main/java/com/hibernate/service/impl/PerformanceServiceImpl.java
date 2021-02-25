package com.hibernate.service.impl;

import com.hibernate.dao.PerformanceDao;
import com.hibernate.model.Performance;
import com.hibernate.service.PerformanceService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PerformanceServiceImpl implements PerformanceService {
    private final PerformanceDao performanceDao;

    @Autowired
    public PerformanceServiceImpl(PerformanceDao performanceDao) {
        this.performanceDao = performanceDao;
    }

    @Override
    public Performance add(Performance performance) {
        return performanceDao.add(performance);
    }

    @Override
    public Performance get(Long id) {
        return performanceDao.get(id).get();
    }

    @Override
    public List<Performance> getAll() {
        return performanceDao.getAll();
    }
}
