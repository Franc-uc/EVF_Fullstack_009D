package com.logistics.usuarios.controller;

import com.logistics.usuarios.model.Usuario;
import com.logistics.usuarios.dto.UsuarioDTO;
import com.logistics.common.exception.ResourceNotFoundException;
import com.logistics.usuarios.service.UsuarioService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> getAllUsuarios() {
        return ResponseEntity.ok(usuarioService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable Long id) {
        Usuario usuario = usuarioService.findById(id);
        return ResponseEntity.ok(usuario);
    }

    @PostMapping
    public ResponseEntity<Usuario> createUsuario(@Valid @RequestBody UsuarioDTO usuarioDTO) {
        if (usuarioService.existsByUsername(usuarioDTO.getNombre())) {
            throw new RuntimeException("El nombre ya está en uso!");
        }
        if (usuarioService.existsByEmail(usuarioDTO.getCorreo())) {
            throw new RuntimeException("El correo ya está en uso!");
        }

        Usuario usuario = new Usuario();
        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setContrasena(usuarioDTO.getContrasena());
        usuario.setCorreo(usuarioDTO.getCorreo());

        usuario.setRol(usuarioDTO.getRol());

        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(usuario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable Long id, @Valid @RequestBody UsuarioDTO usuarioDTO) {
        Usuario existingUsuario = usuarioService.findById(id);
        existingUsuario.setNombre(usuarioDTO.getNombre());

        existingUsuario.setCorreo(usuarioDTO.getCorreo());

        existingUsuario.setRol(usuarioDTO.getRol());

        return ResponseEntity.ok(usuarioService.save(existingUsuario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
        usuarioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}