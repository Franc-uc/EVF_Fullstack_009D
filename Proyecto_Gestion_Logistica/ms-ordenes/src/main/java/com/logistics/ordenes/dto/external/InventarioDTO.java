package com.logistics.ordenes.dto.external;

public class InventarioDTO {
    private Long id;
    private Long productoId;
    private Integer cantidad;
    private String bodegaUbicacion;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getProductoId() { return productoId; }
    public void setProductoId(Long productoId) { this.productoId = productoId; }
    public Integer getCantidad() { return cantidad; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }
    public String getBodegaUbicacion() { return bodegaUbicacion; }
    public void setBodegaUbicacion(String bodegaUbicacion) { this.bodegaUbicacion = bodegaUbicacion; }
}
