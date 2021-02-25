package com.hibernate.model.dto.mapping.impl.request;

import com.hibernate.model.Stage;
import com.hibernate.model.dto.mapping.DtoRequestMapper;
import com.hibernate.model.dto.request.StageRequestDto;
import org.springframework.stereotype.Component;

@Component
public class StageRequestMapper implements
        DtoRequestMapper<StageRequestDto, Stage> {
    @Override
    public Stage fromDto(StageRequestDto dto) {
        Stage stage = new Stage();
        stage.setDescription(dto.getDescription());
        stage.setCapacity(dto.getCapacity());
        return stage;
    }
}
