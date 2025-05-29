package api.service;

import api.repository.IRepository;

import java.util.List;

public abstract class AbstractService<T> {
    protected final IRepository<T> repository;

    public AbstractService(IRepository<T> repository) {
        this.repository = repository;
    }

    public void add(T entity) {
        repository.add(entity);
    }

    public T getById(String id) {
        return repository.getById(id);
    }

    public List<T> getAll() {
        return repository.getAll();
    }

    public boolean update(String id, T updatedEntity) {
        return repository.update(id, updatedEntity);
    }

    public boolean delete(String id) {
        return repository.delete(id);
    }
}
