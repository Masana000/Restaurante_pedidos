package api.controller.impl;

import api.controller.AbstractController;
import api.model.ItemPedido;
import api.service.AbstractService;

public class ItemPedidoController extends AbstractController<ItemPedido> {

    public ItemPedidoController(AbstractService<ItemPedido> service) {
        super(service);
    }

}
