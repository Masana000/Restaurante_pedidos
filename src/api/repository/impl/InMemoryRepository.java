package api.repository.impl;

import api.repository.IRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.logging.Logger;
import java.lang.reflect.Method;

public class InMemoryRepository<T> implements IRepository<T> {
    private static final Logger LOGGER = Logger.getLogger(InMemoryRepository.class.getName());
    private final Map<String, T> storage = new ConcurrentHashMap<>();
    private Function<T, String> idExtractor;

    public InMemoryRepository() {

        this.idExtractor = entity -> {
            try {
                Method getIdMethod = entity.getClass().getMethod("getId");
                return (String) getIdMethod.invoke(entity);
            } catch (Exception e) {
                LOGGER.severe("Erro ao extrair ID da entidade. Certifique-se de que a entidade tem um método 'getId()': " + e.getMessage());
                throw new RuntimeException("Entidade não possui método 'getId()'", e);
            }
        };
    }

    public InMemoryRepository(Function<T, String> idExtractor) {
        this.idExtractor = idExtractor;
    }

    @Override
    public void add(T entity) {
        String id = idExtractor.apply(entity);
        if (id == null || id.isEmpty()) {
            LOGGER.warning("Tentativa de adicionar entidade sem ID válido.");
            return;
        }
        storage.put(id, entity);
        LOGGER.info("Entidade adicionada: " + id);
    }

    @Override
    public T getById(String id) {
        LOGGER.info("Buscando entidade com ID: " + id);
        return storage.get(id);
    }

    @Override
    public List<T> getAll() {
        LOGGER.info("Buscando todas as entidades.");
        return new ArrayList<>(storage.values());
    }

    @Override
    public boolean update(String id, T updatedEntity) {
        if (storage.containsKey(id)) {
            storage.put(id, updatedEntity);
            LOGGER.info("Entidade atualizada: " + id);
            return true;
        }
        LOGGER.warning("Tentativa de atualizar entidade não existente: " + id);
        return false;
    }

    @Override
    public boolean delete(String id) {
        if (storage.containsKey(id)) {
            storage.remove(id);
            LOGGER.info("Entidade removida: " + id);
            return true;
        }
        LOGGER.warning("Tentativa de remover entidade não existente: " + id);
        return false;
    }
}
