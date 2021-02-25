package com.hibernate.controller;

import com.hibernate.model.Stage;
import com.hibernate.model.dto.mapping.DtoRequestMapper;
import com.hibernate.model.dto.mapping.DtoResponseMapper;
import com.hibernate.model.dto.request.StageRequestDto;
import com.hibernate.model.dto.response.StageResponseDto;
import com.hibernate.service.StageService;
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
@RequestMapping("/stages")
public class StageController {
    private final StageService stageService;
    private final DtoResponseMapper<StageResponseDto, Stage> responseMapper;
    private final DtoRequestMapper<StageRequestDto, Stage> requestMapper;

    @Autowired
    public StageController(StageService stageService,
                           DtoResponseMapper<StageResponseDto, Stage> responseMapper,
                           DtoRequestMapper<StageRequestDto, Stage> requestMapper) {
        this.stageService = stageService;
        this.responseMapper = responseMapper;
        this.requestMapper = requestMapper;
    }

    @PostMapping
    public void add(@RequestBody @Valid StageRequestDto dto) {
        stageService.add(requestMapper.fromDto(dto));
    }

    @GetMapping
    public List<StageResponseDto> getAll() {
        return stageService.getAll().stream()
                .map(responseMapper::toDto)
                .collect(Collectors.toList());
    }
}
