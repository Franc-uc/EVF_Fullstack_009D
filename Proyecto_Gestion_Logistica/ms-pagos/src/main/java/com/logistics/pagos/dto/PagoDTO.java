package com.logistics.pagos.dto;

import com.logistics.pagos.model.EstadoPago;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class PagoDTO {
    private Long id;

    @NotNull(message = "El ID del pedido no puede ser nulo")
    private Long pedidoId;

    @NotNull(message = "El monto no puede ser nulo")
    @Positive(message = "El monto debe ser mayor a cero")
    @DecimalMin(value = "0.01", message = "El monto debe ser al menos 0.01")
    private Double monto;

    @NotBlank(message = "El método de pago no puede estar vacío")
    private String metodoPago;

    @NotNull(message = "El estado del pago no puede ser nulo")
    private EstadoPago estado;
    /*    PENDIENTE,
          COMPLETADO,
          RECHAZADO,
          REEMBOLSADO */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(Long pedidoId) {
        this.pedidoId = pedidoId;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public EstadoPago getEstado() {
        return estado;
    }

    public void setEstado(EstadoPago estado) {
        this.estado = estado;
    }
}
