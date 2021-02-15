package com.hibernate.model.dto.mapping.impl.request;

import com.hibernate.model.CinemaHall;
import com.hibernate.model.dto.mapping.DtoRequestMapper;
import com.hibernate.model.dto.request.CinemaHallRequestDto;
import org.springframework.stereotype.Component;

@Component
public class CinemaHallRequestMapper implements
        DtoRequestMapper<CinemaHallRequestDto, CinemaHall> {
    @Override
    public CinemaHall fromDto(CinemaHallRequestDto dto) {
        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setDescription(dto.getDescription());
        cinemaHall.setCapacity(dto.getCapacity());
        cinemaHall.setId(dto.getId());
        return cinemaHall;
    }
}
