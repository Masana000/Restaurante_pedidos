package api.service.impl;

import api.model.Pedido;
import api.repository.IRepository;
import api.service.AbstractService;
public class PedidoService extends AbstractService<Pedido> {

    public PedidoService(IRepository<Pedido> repository) {
        super(repository);
    }

}
