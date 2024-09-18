package org.BatiCuisine.Dao.Interfaces;

import java.util.UUID;

public interface DAO<T> {
    void create(T t);

    T read(UUID id);

    void update(T t);

    boolean delete(UUID id);
}
