package api.controller;

import api.service.AbstractService;

import java.util.List;
public abstract class AbstractController<T> {
    protected final AbstractService<T> service;

    public AbstractController(AbstractService<T> service) {
        this.service = service;
    }

    public void add(T entity) {
        service.add(entity);
    }

    public T getById(String id) {
        return service.getById(id);
    }

    public List<T> getAll() {
        return service.getAll();
    }

    public boolean update(String id, T updatedEntity) {
        return service.update(id, updatedEntity);
    }

    public boolean delete(String id) {
        return service.delete(id);
    }
}
