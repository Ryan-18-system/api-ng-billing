package dev.ryan.nobrega.application.mapper;

public interface Mapper<T,U> {
    U map(T entidade);
}
