package com.hibernate.model.dto.mapping;

public interface DtoResponseMapper<T, E> {
    T toDto(E dto);
}
