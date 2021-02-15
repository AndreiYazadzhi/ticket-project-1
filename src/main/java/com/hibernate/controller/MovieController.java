package com.hibernate.controller;

import com.hibernate.model.Movie;
import com.hibernate.model.dto.mapping.DtoRequestMapper;
import com.hibernate.model.dto.mapping.DtoResponseMapper;
import com.hibernate.model.dto.request.MovieRequestDto;
import com.hibernate.model.dto.response.MovieResponseDto;
import com.hibernate.service.MovieService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private final MovieService movieService;
    private final DtoResponseMapper<MovieResponseDto, Movie> responseMapper;
    private final DtoRequestMapper<MovieRequestDto, Movie> requestMapper;

    @Autowired
    public MovieController(MovieService movieService, DtoResponseMapper<MovieResponseDto,
            Movie> responseMapper, DtoRequestMapper<MovieRequestDto, Movie> requestMapper) {
        this.movieService = movieService;
        this.responseMapper = responseMapper;
        this.requestMapper = requestMapper;
    }

    @PostMapping
    public void add(@RequestBody MovieRequestDto dto) {
        movieService.add(requestMapper.fromDto(dto));
    }

    @GetMapping
    public List<MovieResponseDto> getAll() {
        return movieService.getAll().stream()
                .map(responseMapper::toDto)
                .collect(Collectors.toList());
    }
}

