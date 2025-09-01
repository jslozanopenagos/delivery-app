package com.solvd.delivery.repository;

import java.util.List;
import java.util.Optional;

public interface Repositable<T> {
    void add(T entity);
    void remove(T entity);
    List<T> findAll();
    Optional<T> findById(long id);
}
