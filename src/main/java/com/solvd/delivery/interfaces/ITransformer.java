package com.solvd.delivery.interfaces;

@FunctionalInterface
public interface ITransformer<T, R> {
    R transform(T t);
}
