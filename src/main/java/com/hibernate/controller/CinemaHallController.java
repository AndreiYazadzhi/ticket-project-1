package com.hibernate.controller;

import com.hibernate.model.CinemaHall;
import com.hibernate.model.dto.mapping.DtoRequestMapper;
import com.hibernate.model.dto.mapping.DtoResponseMapper;
import com.hibernate.model.dto.request.CinemaHallRequestDto;
import com.hibernate.model.dto.response.CinemaHallResponseDto;
import com.hibernate.service.CinemaHallService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cinema-halls")
public class CinemaHallController {
    private final CinemaHallService cinemaHallService;
    private final DtoResponseMapper<CinemaHallResponseDto, CinemaHall> responseMapper;
    private final DtoRequestMapper<CinemaHallRequestDto, CinemaHall> requestMapper;

    @Autowired
    public CinemaHallController(CinemaHallService cinemaHallService,
                                DtoResponseMapper<CinemaHallResponseDto, CinemaHall> responseMapper,
                                DtoRequestMapper<CinemaHallRequestDto, CinemaHall> requestMapper) {
        this.cinemaHallService = cinemaHallService;
        this.responseMapper = responseMapper;
        this.requestMapper = requestMapper;
    }

    @PostMapping
    public void add(@RequestBody CinemaHallRequestDto dto) {
        cinemaHallService.add(requestMapper.fromDto(dto));
    }

    @GetMapping
    public List<CinemaHallResponseDto> getAll() {
        return cinemaHallService.getAll().stream()
                .map(responseMapper::toDto)
                .collect(Collectors.toList());
    }
}
