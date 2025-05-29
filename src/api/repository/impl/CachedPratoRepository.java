package api.repository.impl;

import api.model.Prato;
import api.repository.IRepository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

public class CachedPratoRepository extends InMemoryRepository<Prato> {
    private static final Logger LOGGER = Logger.getLogger(CachedPratoRepository.class.getName());
    private final IRepository<Prato> decoratedRepository;
    private final Map<String, Prato> cache = new ConcurrentHashMap<>();

    public CachedPratoRepository(IRepository<Prato> decoratedRepository) {

        super();
        this.decoratedRepository = decoratedRepository;
        LOGGER.info("CachedPratoRepository inicializado com decoração de " + decoratedRepository.getClass().getSimpleName());
    }

    @Override
    public void add(Prato prato) {
        decoratedRepository.add(prato);
        cache.put(prato.getId(), prato); 
        LOGGER.info("Prato adicionado e cache atualizado para ID: " + prato.getId());
    }

    @Override
    public Prato getById(String id) {
        if (cache.containsKey(id)) {
            LOGGER.info("Prato encontrado no cache para ID: " + id);
            return cache.get(id);
        }

        Prato prato = decoratedRepository.getById(id);
        if (prato != null) {
            cache.put(id, prato);
            LOGGER.info("Prato não estava no cache, buscado do repositório e adicionado ao cache para ID: " + id);
        } else {
            LOGGER.info("Prato não encontrado no repositório para ID: " + id);
        }
        return prato;
    }

    @Override
    public List<Prato> getAll() {

        List<Prato> allPratos = decoratedRepository.getAll();
        cache.clear(); 
        allPratos.forEach(prato -> cache.put(prato.getId(), prato));
        LOGGER.info("Todos os pratos buscados do repositório e cache atualizado.");
        return allPratos;
    }

    @Override
    public boolean update(String id, Prato updatedPrato) {
        boolean success = decoratedRepository.update(id, updatedPrato);
        if (success) {
            cache.put(id, updatedPrato); 
            LOGGER.info("Prato atualizado e cache atualizado para ID: " + id);
        } else {
            LOGGER.warning("Falha ao atualizar prato com ID: " + id + ". Cache não atualizado.");
        }
        return success;
    }

    @Override
    public boolean delete(String id) {
        boolean success = decoratedRepository.delete(id);
        if (success) {
            cache.remove(id); 
            LOGGER.info("Prato removido e cache atualizado para ID: " + id);
        } else {
            LOGGER.warning("Falha ao remover prato com ID: " + id + ". Cache não atualizado.");
        }
        return success;
    }

    public void clearCache() {
        cache.clear();
        LOGGER.info("Cache de pratos limpo.");
    }
}
