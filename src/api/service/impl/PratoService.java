package api.service.impl;

import api.model.Prato;
import api.repository.IRepository;
import api.service.AbstractService;

public class PratoService extends AbstractService<Prato> {

    public PratoService(IRepository<Prato> repository) {
        super(repository);
    }

}
