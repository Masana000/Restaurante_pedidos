package api.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Pedido {
    private String id;
    private String mesa;
    private LocalDateTime hora;
    private StatusPedido status;

    public Pedido(String id, String mesa) {
        this.id = id;
        this.mesa = mesa;
        this.hora = LocalDateTime.now(); // Define a hora atual automaticamente
        this.status = StatusPedido.EM_PREPARO; // Status inicial padrão
    }

    // Getters e Setters
    public String getId() {
        return id;
    }

    public String getMesa() {
        return mesa;
    }

    public String getHora() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return hora.format(formatter);
    }

    public StatusPedido getStatus() {
        return status;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setMesa(String mesa) {
        this.mesa = mesa;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Pedido{" +
               "id='" + id + '\'' +
               ", mesa='" + mesa + '\'' +
               ", hora=" + getHora() +
               ", status=" + status.getDescricao() +
               '}';
    }
}