package com.hibernate.model.dto.mapping.impl.request;

import com.hibernate.model.PerformanceSession;
import com.hibernate.model.dto.mapping.DtoRequestMapper;
import com.hibernate.model.dto.request.PerformanceSessionRequestDto;
import com.hibernate.service.StageService;
import com.hibernate.service.PerformanceService;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PerformanceSessionRequestMapper implements
        DtoRequestMapper<PerformanceSessionRequestDto, PerformanceSession> {
    private final PerformanceService performanceService;
    private final StageService stageService;

    @Autowired
    public PerformanceSessionRequestMapper(PerformanceService performanceService,
                                           StageService stageService) {
        this.performanceService = performanceService;
        this.stageService = stageService;
    }

    @Override
    public PerformanceSession fromDto(PerformanceSessionRequestDto dto) {
        PerformanceSession performanceSession = new PerformanceSession();
        performanceSession.setMovie(performanceService.get(dto.getMovieId()));
        performanceSession.setCinemaHall(stageService.get(dto.getCinemaHallId()));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        performanceSession.setShowTime(LocalDateTime.parse(dto.getShowTime(), formatter));
        return performanceSession;
    }
}
