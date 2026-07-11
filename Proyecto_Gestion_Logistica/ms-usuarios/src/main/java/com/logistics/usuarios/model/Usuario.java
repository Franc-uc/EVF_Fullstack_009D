package com.logistics.usuarios.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre no puede quedar vacío.")
    @Size(min = 3, max = 50, message = "El nombre debe contener entre 3 y 50 caracteres.")
    private String nombre;

    @NotBlank(message = "La contraseña no puede quedar vacía.")
    @Size(min = 6, message = "La contraseña debe contener al menos 6 caracteres")
    private String contrasena;

    @NotBlank(message = "El correo electrónico no puede quedar vacío.")
    @Email(message = "Ingresa un formato de correo válido.")
    private String correo;

    private String rol;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String email) {
        this.correo = email;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
