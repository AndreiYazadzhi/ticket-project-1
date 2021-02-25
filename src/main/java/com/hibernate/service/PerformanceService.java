package com.hibernate.service;

import com.hibernate.model.Performance;
import java.util.List;

public interface PerformanceService {
    Performance add(Performance performance);

    Performance get(Long id);

    List<Performance> getAll();
}
