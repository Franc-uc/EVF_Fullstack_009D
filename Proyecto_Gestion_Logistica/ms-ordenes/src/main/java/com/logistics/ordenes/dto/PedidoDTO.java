package com.logistics.ordenes.dto;

import com.logistics.ordenes.model.EstadoPedido;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

public class PedidoDTO {
    private Long id;
    private LocalDateTime fechaPedido;

    @NotNull(message = "El estado del pedido no puede ser nulo")
    private EstadoPedido estado;

    @Valid
    private List<DetallePedidoDTO> items;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(LocalDateTime fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }

    public List<DetallePedidoDTO> getItems() {
        return items;
    }

    public void setItems(List<DetallePedidoDTO> items) {
        this.items = items;
    }
}
