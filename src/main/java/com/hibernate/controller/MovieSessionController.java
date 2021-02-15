package com.hibernate.controller;

import com.hibernate.model.MovieSession;
import com.hibernate.model.dto.mapping.DtoRequestMapper;
import com.hibernate.model.dto.mapping.DtoResponseMapper;
import com.hibernate.model.dto.request.MovieSessionRequestDto;
import com.hibernate.model.dto.response.MovieSessionResponseDto;
import com.hibernate.service.MovieSessionService;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
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
@RequestMapping("/movie-sessions")
public class MovieSessionController {
    private final MovieSessionService movieSessionService;
    private final DtoResponseMapper<MovieSessionResponseDto, MovieSession> responseMapper;
    private final DtoRequestMapper<MovieSessionRequestDto, MovieSession> requestMapper;

    @Autowired
    public MovieSessionController(MovieSessionService movieSessionService,
                                  DtoResponseMapper<MovieSessionResponseDto,
                                          MovieSession> responseMapper,
                                  DtoRequestMapper<MovieSessionRequestDto,
                                          MovieSession> requestMapper) {
        this.movieSessionService = movieSessionService;
        this.responseMapper = responseMapper;
        this.requestMapper = requestMapper;
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id,
                       @RequestBody MovieSessionRequestDto movieSessionRequestDto) {
        MovieSession movieSession = requestMapper.fromDto(movieSessionRequestDto);
        movieSession.setId(id);
        movieSessionService.update(movieSession);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        movieSessionService.remove(id);
    }

    @PostMapping
    public void add(@RequestBody MovieSessionRequestDto dto) {
        movieSessionService.add(requestMapper.fromDto(dto));
    }

    @GetMapping
    public List<MovieSessionResponseDto> getAll() {
        return movieSessionService.getAll().stream()
                .map(responseMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/available")
    public List<MovieSessionResponseDto> getAvailable(@RequestParam Long movieId,
                                                      @RequestParam
                                                      @DateTimeFormat(pattern = "dd.MM.yyyy")
                                                              LocalDate date) {
        return movieSessionService.findAvailableSessions(movieId, date).stream()
                .map(responseMapper::toDto)
                .collect(Collectors.toList());
    }
}

