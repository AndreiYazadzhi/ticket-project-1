package com.hibernate.model.dto.mapping.impl.response;

import com.hibernate.model.Movie;
import com.hibernate.model.dto.mapping.DtoResponseMapper;
import com.hibernate.model.dto.response.MovieResponseDto;
import org.springframework.stereotype.Component;

@Component
public class MovieResponseMapper implements
        DtoResponseMapper<MovieResponseDto, Movie> {
    @Override
    public MovieResponseDto toDto(Movie movie) {
        MovieResponseDto movieResponseDto = new MovieResponseDto();
        movieResponseDto.setDescription(movie.getDescription());
        movieResponseDto.setTitle(movie.getTitle());
        return movieResponseDto;
    }
}
