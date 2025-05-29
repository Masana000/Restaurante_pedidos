package api.model;

public class ItemPedido {
    private String id;
    private String pedidoId; 
    private String pratoId;  
    private int quantidade;
    private double precoUnitario; 
    public ItemPedido(String id, String pedidoId, String pratoId, int quantidade, double precoUnitario) {
        this.id = id;
        this.pedidoId = pedidoId;
        this.pratoId = pratoId;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
    }

    public String getId() {
        return id;
    }

    public String getPedidoId() {
        return pedidoId;
    }

    public String getPratoId() {
        return pratoId;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPedidoId(String pedidoId) {
        this.pedidoId = pedidoId;
    }

    public void setPratoId(String pratoId) {
        this.pratoId = pratoId;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public void setPrecoUnitario(double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    @Override
    public String toString() {
        return "ItemPedido{" +
               "id='" + id + '\'' +
               ", pedidoId='" + pedidoId + '\'' +
               ", pratoId='" + pratoId + '\'' +
               ", quantidade=" + quantidade +
               ", precoUnitario=" + precoUnitario +
               '}';
    }
}
