package api.service.impl;

import api.model.ItemPedido;
import api.repository.IRepository;
import api.service.AbstractService;

public class ItemPedidoService extends AbstractService<ItemPedido> {

    public ItemPedidoService(IRepository<ItemPedido> repository) {
        super(repository);
    }

}
