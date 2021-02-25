package com.hibernate.controller;

import com.hibernate.model.PerformanceSession;
import com.hibernate.model.dto.mapping.DtoRequestMapper;
import com.hibernate.model.dto.mapping.DtoResponseMapper;
import com.hibernate.model.dto.request.PerformanceSessionRequestDto;
import com.hibernate.model.dto.response.PerformanceSessionResponseDto;
import com.hibernate.service.PerformanceSessionService;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/performance-sessions")
public class PerformanceSessionController {
    private final PerformanceSessionService performanceSessionService;
    private final DtoResponseMapper<PerformanceSessionResponseDto, PerformanceSession> responseMapper;
    private final DtoRequestMapper<PerformanceSessionRequestDto, PerformanceSession> requestMapper;

    @Autowired
    public PerformanceSessionController(PerformanceSessionService performanceSessionService,
                                        DtoResponseMapper<PerformanceSessionResponseDto,
                                               PerformanceSession> responseMapper,
                                        DtoRequestMapper<PerformanceSessionRequestDto,
                                               PerformanceSession> requestMapper) {
        this.performanceSessionService = performanceSessionService;
        this.responseMapper = responseMapper;
        this.requestMapper = requestMapper;
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id,
                       @RequestBody PerformanceSessionRequestDto performanceSessionRequestDto) {
        PerformanceSession performanceSession = requestMapper.fromDto(performanceSessionRequestDto);
        performanceSession.setId(id);
        performanceSessionService.update(performanceSession);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        performanceSessionService.remove(id);
    }

    @PostMapping
    public void add(@RequestBody @Valid PerformanceSessionRequestDto dto) {
        performanceSessionService.add(requestMapper.fromDto(dto));
    }

    @GetMapping
    public List<PerformanceSessionResponseDto> getAll() {
        return performanceSessionService.getAll().stream()
                .map(responseMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/available")
    public List<PerformanceSessionResponseDto> getAvailable(@RequestParam Long movieId,
                                                            @RequestParam
                                                      @DateTimeFormat(pattern = "dd.MM.yyyy")
                                                              LocalDate date) {
        return performanceSessionService.findAvailableSessions(movieId, date).stream()
                .map(responseMapper::toDto)
                .collect(Collectors.toList());
    }
}

