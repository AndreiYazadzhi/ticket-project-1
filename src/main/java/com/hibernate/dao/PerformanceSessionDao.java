package com.hibernate.dao;

import com.hibernate.model.PerformanceSession;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PerformanceSessionDao {
    PerformanceSession add(PerformanceSession performanceSession);

    Optional<PerformanceSession> get(Long id);

    void update(PerformanceSession performanceSession);

    void remove(Long id);

    List<PerformanceSession> getAll();

    List<PerformanceSession> findAvailableSessions(Long movieId, LocalDate date);
}
