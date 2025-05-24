package dev.ryan.nobrega.application.mapper;

public interface Mapper<T,U> {
    U toDTO(T entidade);

    T toEntity(U dto);
}
