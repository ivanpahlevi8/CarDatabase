package com.example.car.repository;

import java.util.Collection;

public interface CommonRepository<T> {
    public Iterable<T> save(Collection<T> domain);
    public Iterable<T> findAll();
    public T findById(String id);
    public T findByBrand(String id);
    public T findByType(String type);
    public T findByCode(String code);
    public T findByColor(String color);
    public T delete(T domain);
    public T save(T domain);
}
