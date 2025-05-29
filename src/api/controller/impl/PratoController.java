package api.controller.impl;

import api.controller.AbstractController;
import api.model.Prato;
import api.service.AbstractService;

public class PratoController extends AbstractController<Prato> {

    public PratoController(AbstractService<Prato> service) {
        super(service);
    }

}
