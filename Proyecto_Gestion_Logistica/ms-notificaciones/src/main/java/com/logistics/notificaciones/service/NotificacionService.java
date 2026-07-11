package com.logistics.notificaciones.service;

import com.logistics.notificaciones.model.Notificacion;
import com.logistics.notificaciones.repository.NotificacionRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import com.logistics.common.exception.ResourceNotFoundException;

@Service
public class NotificacionService {

    private NotificacionRepository notificacionRepository;

    public NotificacionService(NotificacionRepository notificacionRepository) {
        this.notificacionRepository = notificacionRepository;
    }

    public List<Notificacion> findAll() {
        return notificacionRepository.findAll();
    }

    public Notificacion findById(Long id) {
        return notificacionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Notificación no encontrada con id: " + id));
    }

    public Notificacion save(Notificacion notificacion) {
        return notificacionRepository.save(notificacion);
    }

    public void deleteById(Long id) {
        notificacionRepository.deleteById(id);
    }

    public List<Notificacion> findByUsuarioId(Long usuarioId) {
        return notificacionRepository.findByUsuarioId(usuarioId);
    }

    public List<Notificacion> findUnreadByUsuarioId(Long usuarioId) {
        return notificacionRepository.findByUsuarioIdAndLeidaFalse(usuarioId);
    }

    public Notificacion markAsRead(Long id) {
        Notificacion notificacion = notificacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Notificación no encontrada con id: " + id));
        notificacion.setLeida(true);
        return notificacionRepository.save(notificacion);
    }
}
