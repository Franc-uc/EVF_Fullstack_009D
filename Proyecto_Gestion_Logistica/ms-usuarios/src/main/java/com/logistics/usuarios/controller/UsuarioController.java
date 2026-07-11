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

    private UsuarioService usuarioService;

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
        if (usuarioService.existsByUsername(usuarioDTO.getUsername())) {
            throw new RuntimeException("El nombre de usuario ya está en uso!");
        }
        if (usuarioService.existsByEmail(usuarioDTO.getEmail())) {
            throw new RuntimeException("El correo electrónico ya está en uso!");
        }
        Usuario usuario = new Usuario();
        usuario.setUsername(usuarioDTO.getUsername());
        usuario.setPassword(usuarioDTO.getPassword()); // En un caso real, se encriptaría
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setRol(usuarioDTO.getRol());
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(usuario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable Long id, @Valid @RequestBody UsuarioDTO usuarioDTO) {
        Usuario existingUsuario = usuarioService.findById(id);
        existingUsuario.setUsername(usuarioDTO.getUsername());
        existingUsuario.setEmail(usuarioDTO.getEmail());
        existingUsuario.setRol(usuarioDTO.getRol());
        // No se actualiza la contraseña directamente aquí por seguridad, se haría en otro endpoint
        return ResponseEntity.ok(usuarioService.save(existingUsuario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
        usuarioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
