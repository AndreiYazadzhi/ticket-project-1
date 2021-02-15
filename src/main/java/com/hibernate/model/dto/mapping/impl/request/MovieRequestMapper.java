package com.hibernate.model.dto.mapping.impl.request;

import com.hibernate.model.Movie;
import com.hibernate.model.dto.mapping.DtoRequestMapper;
import com.hibernate.model.dto.request.MovieRequestDto;
import org.springframework.stereotype.Component;

@Component
public class MovieRequestMapper implements
        DtoRequestMapper<MovieRequestDto, Movie> {
    @Override
    public Movie fromDto(MovieRequestDto dto) {
        Movie movie = new Movie();
        movie.setDescription(dto.getDescription());
        movie.setTitle(dto.getTitle());
        return movie;
    }
}
