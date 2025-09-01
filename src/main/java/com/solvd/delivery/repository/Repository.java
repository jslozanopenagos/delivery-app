package com.solvd.delivery.repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

public class Repository<T> implements Repositable<T> {
    private final List<T> storage = new ArrayList<>();
    protected static final Logger LOGGER = LogManager.getLogger(Repository.class);

    @Override
    public void add(T entity) {
        storage.add(entity);
    }

    @Override
    public void remove(T entity) {
        storage.remove(entity);
    }

    @Override
    public List<T> findAll() {
        return new ArrayList<>(storage);
    }

    @Override
    public Optional<T> findById(long id) {
        for (T e : storage) {
            try {
                long currentId = (long) e.getClass().getMethod("getId").invoke(e);
                if (currentId == id) {
                    return Optional.of(e);
                }
            } catch (Exception ex) {
                LOGGER.error(ex.getMessage());
            }
        }
        return Optional.empty();
    }
}