package api.model;

public enum StatusPedido {
    EM_PREPARO("Em preparo"),
    PRONTO_PARA_ENTREGA("Pronto para entrega"),
    FINALIZADO("Finalizado"),
    CANCELADO("Cancelado");

    private final String descricao;

    StatusPedido(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public static StatusPedido fromDescricao(String descricao) {
        for (StatusPedido status : StatusPedido.values()) {
            if (status.getDescricao().equalsIgnoreCase(descricao)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Status inválido: " + descricao);
    }
}