package com.logistics.stock.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "inventarios")
public class Inventario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El ID del producto no puede ser nulo")
    private Long productoId; // Referencia lógica al MS Catálogo

    @Min(value = 0, message = "La cantidad en stock no puede ser negativa")
    private Integer cantidad;

    private String bodegaUbicacion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductoId() {
        return productoId;
    }

    public void setProductoId(Long productoId) {
        this.productoId = productoId;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getBodegaUbicacion() {
        return bodegaUbicacion;
    }

    public void setBodegaUbicacion(String bodegaUbicacion) {
        this.bodegaUbicacion = bodegaUbicacion;
    }
}
