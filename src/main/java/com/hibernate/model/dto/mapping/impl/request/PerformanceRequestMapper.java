package com.hibernate.model.dto.mapping.impl.request;

import com.hibernate.model.Performance;
import com.hibernate.model.dto.mapping.DtoRequestMapper;
import com.hibernate.model.dto.request.PerformanceRequestDto;
import org.springframework.stereotype.Component;

@Component
public class PerformanceRequestMapper implements
        DtoRequestMapper<PerformanceRequestDto, Performance> {
    @Override
    public Performance fromDto(PerformanceRequestDto dto) {
        Performance performance = new Performance();
        performance.setDescription(dto.getDescription());
        performance.setTitle(dto.getTitle());
        return performance;
    }
}
