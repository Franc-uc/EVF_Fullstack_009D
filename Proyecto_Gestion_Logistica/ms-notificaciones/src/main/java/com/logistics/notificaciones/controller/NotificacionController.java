package com.logistics.notificaciones.controller;

import com.logistics.notificaciones.model.Notificacion;
import com.logistics.notificaciones.dto.NotificacionDTO;
import com.logistics.common.exception.ResourceNotFoundException;
import com.logistics.notificaciones.service.NotificacionService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/notificaciones")
public class NotificacionController {

    private NotificacionService notificacionService;

    public NotificacionController(NotificacionService notificacionService) {
        this.notificacionService = notificacionService;
    }

    @GetMapping
    public ResponseEntity<List<Notificacion>> getAllNotificaciones() {
        return ResponseEntity.ok(notificacionService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Notificacion> getNotificacionById(@PathVariable Long id) {
        Notificacion notificacion = notificacionService.findById(id);
        return ResponseEntity.ok(notificacion);
    }

    @PostMapping
    public ResponseEntity<Notificacion> createNotificacion(@Valid @RequestBody NotificacionDTO notificacionDTO) {
        Notificacion notificacion = new Notificacion();
        notificacion.setUsuarioId(notificacionDTO.getUsuarioId());
        notificacion.setMensaje(notificacionDTO.getMensaje());
        notificacion.setTipo(notificacionDTO.getTipo());
        notificacion.setLeida(notificacionDTO.isLeida());
        return ResponseEntity.status(HttpStatus.CREATED).body(notificacionService.save(notificacion));
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Notificacion>> getNotificacionesByUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(notificacionService.findByUsuarioId(usuarioId));
    }

    @GetMapping("/usuario/{usuarioId}/unread")
    public ResponseEntity<List<Notificacion>> getUnreadNotificacionesByUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(notificacionService.findUnreadByUsuarioId(usuarioId));
    }

    @PutMapping("/{id}/mark-as-read")
    public ResponseEntity<Notificacion> markNotificacionAsRead(@PathVariable Long id) {
        try {
            Notificacion updatedNotificacion = notificacionService.markAsRead(id);
            return ResponseEntity.ok(updatedNotificacion);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotificacion(@PathVariable Long id) {
        notificacionService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
