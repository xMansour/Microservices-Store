package com.mansour.inventoryservice.service;

import java.util.List;

public interface BaseService<T, R, V> {
    R create(T t);

    R get(V v);

    void delete(V v);

    R update(T t, V v);

    List<R> getAll();
}
