package com.hibernate.model.dto.mapping.impl.response;

import com.hibernate.model.CinemaHall;
import com.hibernate.model.dto.mapping.DtoResponseMapper;
import com.hibernate.model.dto.response.CinemaHallResponseDto;
import org.springframework.stereotype.Component;

@Component
public class CinemaHallResponseMapper implements
        DtoResponseMapper<CinemaHallResponseDto, CinemaHall> {
    @Override
    public CinemaHallResponseDto toDto(CinemaHall cinemaHall) {
        CinemaHallResponseDto cinemaHallResponseDto = new CinemaHallResponseDto();
        cinemaHallResponseDto.setDescription(cinemaHall.getDescription());
        cinemaHallResponseDto.setId(cinemaHall.getId());
        return cinemaHallResponseDto;
    }
}
