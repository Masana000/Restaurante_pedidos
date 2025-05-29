package api.controller.impl;

import api.controller.AbstractController;
import api.model.Pedido;
import api.service.AbstractService;

public class PedidoController extends AbstractController<Pedido> {

    public PedidoController(AbstractService<Pedido> service) {
        super(service);
    }

}
