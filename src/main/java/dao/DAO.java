package dao;

import java.util.List;
import java.util.Optional;

public interface DAO<T> {
    T get(long id);

    List<T> getAll();

    T create(T t);

    void update(T t, T t2);

    void delete(T t);
}
