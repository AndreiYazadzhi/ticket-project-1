package com.hibernate.service;

import com.hibernate.model.PerformanceSession;
import java.time.LocalDate;
import java.util.List;

public interface PerformanceSessionService {
    List<PerformanceSession> findAvailableSessions(Long movieId, LocalDate date);

    List<PerformanceSession> getAll();

    void update(PerformanceSession performanceSession);

    void remove(Long id);

    PerformanceSession get(Long id);

    PerformanceSession add(PerformanceSession session);
}
