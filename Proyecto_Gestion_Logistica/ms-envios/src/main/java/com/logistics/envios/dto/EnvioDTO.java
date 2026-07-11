package com.logistics.envios.dto;

import com.logistics.envios.model.EstadoEnvio;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class EnvioDTO {
    private Long id;

    @NotNull(message = "El ID del pedido no puede ser nulo")
    private Long pedidoId;

    @NotBlank(message = "La dirección de envío no puede estar vacía")
    private String direccionEnvio;

    @NotBlank(message = "La ciudad no puede estar vacía")
    private String ciudad;

    @NotBlank(message = "El código postal no puede estar vacío")
    private String codigoPostal;

    @NotNull(message = "El estado del envío no puede ser nulo")
    private EstadoEnvio estado;

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

    public String getDireccionEnvio() {
        return direccionEnvio;
    }

    public void setDireccionEnvio(String direccionEnvio) {
        this.direccionEnvio = direccionEnvio;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public EstadoEnvio getEstado() {
        return estado;
    }

    public void setEstado(EstadoEnvio estado) {
        this.estado = estado;
    }
}
