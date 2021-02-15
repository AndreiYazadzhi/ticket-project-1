package com.hibernate.model.dto.mapping;

public interface DtoRequestMapper<T, E> {
    E fromDto(T dto);
}
