package api.repository;

import java.util.List;

public interface IRepository<T> {

    void add(T entity);
    T getById(String id);
    List<T> getAll();
    boolean update(String id, T updatedEntity);
    boolean delete(String id);
}
