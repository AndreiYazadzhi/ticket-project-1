package com.hibernate.model.dto.mapping.impl.response;

import com.hibernate.model.MovieSession;
import com.hibernate.model.dto.mapping.DtoResponseMapper;
import com.hibernate.model.dto.response.MovieSessionResponseDto;
import java.time.format.DateTimeFormatter;
import org.springframework.stereotype.Component;

@Component
public class MovieSessionResponseMapper implements
        DtoResponseMapper<MovieSessionResponseDto, MovieSession> {
    @Override
    public MovieSessionResponseDto toDto(MovieSession movieSession) {
        MovieSessionResponseDto movieSessionResponseDto =
                new MovieSessionResponseDto();
        movieSessionResponseDto.setMovieTitle(movieSession.getMovie().getTitle());
        movieSessionResponseDto.setCinemaHallId(movieSession.getCinemaHall().getId());
        movieSessionResponseDto.setShowTime(movieSession.getShowTime()
                .format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")));
        return movieSessionResponseDto;
    }
}
