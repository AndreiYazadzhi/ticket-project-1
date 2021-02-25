package com.hibernate.controller;

import com.hibernate.model.Performance;
import com.hibernate.model.dto.mapping.DtoRequestMapper;
import com.hibernate.model.dto.mapping.DtoResponseMapper;
import com.hibernate.model.dto.request.PerformanceRequestDto;
import com.hibernate.model.dto.response.PerformanceResponseDto;
import com.hibernate.service.PerformanceService;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/performances")
public class PerformanceController {
    private final PerformanceService performanceService;
    private final DtoResponseMapper<PerformanceResponseDto, Performance> responseMapper;
    private final DtoRequestMapper<PerformanceRequestDto, Performance> requestMapper;

    @Autowired
    public PerformanceController(PerformanceService performanceService, DtoResponseMapper<PerformanceResponseDto,
            Performance> responseMapper, DtoRequestMapper<PerformanceRequestDto, Performance> requestMapper) {
        this.performanceService = performanceService;
        this.responseMapper = responseMapper;
        this.requestMapper = requestMapper;
    }

    @PostMapping
    public void add(@RequestBody @Valid PerformanceRequestDto dto) {
        performanceService.add(requestMapper.fromDto(dto));
    }

    @GetMapping
    public List<PerformanceResponseDto> getAll() {
        return performanceService.getAll().stream()
                .map(responseMapper::toDto)
                .collect(Collectors.toList());
    }
}

