package com.hibernate.model.dto.mapping.impl.response;

import com.hibernate.model.Stage;
import com.hibernate.model.dto.mapping.DtoResponseMapper;
import com.hibernate.model.dto.response.StageResponseDto;
import org.springframework.stereotype.Component;

@Component
public class StageResponseMapper implements
        DtoResponseMapper<StageResponseDto, Stage> {
    @Override
    public StageResponseDto toDto(Stage stage) {
        StageResponseDto stageResponseDto = new StageResponseDto();
        stageResponseDto.setDescription(stage.getDescription());
        stageResponseDto.setId(stage.getId());
        return stageResponseDto;
    }
}
