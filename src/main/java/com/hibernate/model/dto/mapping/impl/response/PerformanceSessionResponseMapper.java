package com.hibernate.model.dto.mapping.impl.response;

import com.hibernate.model.PerformanceSession;
import com.hibernate.model.dto.mapping.DtoResponseMapper;
import com.hibernate.model.dto.response.PerformanceSessionResponseDto;
import java.time.format.DateTimeFormatter;
import org.springframework.stereotype.Component;

@Component
public class PerformanceSessionResponseMapper implements
        DtoResponseMapper<PerformanceSessionResponseDto, PerformanceSession> {
    @Override
    public PerformanceSessionResponseDto toDto(PerformanceSession performanceSession) {
        PerformanceSessionResponseDto performanceSessionResponseDto =
                new PerformanceSessionResponseDto();
        performanceSessionResponseDto.setId(performanceSession.getId());
        performanceSessionResponseDto.setMovieTitle(performanceSession.getMovie().getTitle());
        performanceSessionResponseDto.setCinemaHallId(performanceSession.getCinemaHall().getId());
        performanceSessionResponseDto.setShowTime(performanceSession.getShowTime()
                .format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")));
        performanceSessionResponseDto.setMovieId(performanceSession.getMovie().getId());
        return performanceSessionResponseDto;
    }
}
