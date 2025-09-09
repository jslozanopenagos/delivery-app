package com.solvd.delivery.interfaces;

@FunctionalInterface
public interface IValidator<T> {
    boolean validate(T t);
}