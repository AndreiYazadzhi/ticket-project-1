package com.hibernate.model.dto.mapping.impl.response;

import com.hibernate.model.Performance;
import com.hibernate.model.dto.mapping.DtoResponseMapper;
import com.hibernate.model.dto.response.PerformanceResponseDto;
import org.springframework.stereotype.Component;

@Component
public class PerformanceResponseMapper implements
        DtoResponseMapper<PerformanceResponseDto, Performance> {
    @Override
    public PerformanceResponseDto toDto(Performance performance) {
        PerformanceResponseDto performanceResponseDto = new PerformanceResponseDto();
        performanceResponseDto.setId(performance.getId());
        performanceResponseDto.setDescription(performance.getDescription());
        performanceResponseDto.setTitle(performance.getTitle());
        return performanceResponseDto;
    }
}
