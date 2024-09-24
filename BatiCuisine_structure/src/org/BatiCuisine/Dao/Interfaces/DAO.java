package org.BatiCuisine.Dao.Interfaces;

import java.util.List;
import java.util.UUID;

public interface DAO<T> {
    void create(T t);

    T read(UUID id);

    List<T> getAll();
}
