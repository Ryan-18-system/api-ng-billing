package dev.ryan.nobrega.model.mapper;

public interface Mapper<T,U> {
    U map(T entidade);
}
