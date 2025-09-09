package com.solvd.delivery.interfaces;

import java.util.List;
import java.util.Optional;

public interface IRepositoryHandler<T> {
    void add(T entity);
    void remove(T entity);
    List<T> findAll();
    Optional<T> findById(long id);
}
