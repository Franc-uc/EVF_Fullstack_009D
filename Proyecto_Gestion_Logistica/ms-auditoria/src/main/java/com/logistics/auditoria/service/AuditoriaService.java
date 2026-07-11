package com.logistics.auditoria.service;

import com.logistics.auditoria.model.Auditoria;
import com.logistics.auditoria.repository.AuditoriaRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import com.logistics.common.exception.ResourceNotFoundException;

@Service
public class AuditoriaService {

    private AuditoriaRepository auditoriaRepository;

    public AuditoriaService(AuditoriaRepository auditoriaRepository) {
        this.auditoriaRepository = auditoriaRepository;
    }

    public List<Auditoria> findAll() {
        return auditoriaRepository.findAll();
    }

    public Auditoria findById(Long id) {
        return auditoriaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Auditoría no encontrada con id: " + id));
    }

    public Auditoria save(Auditoria auditoria) {
        return auditoriaRepository.save(auditoria);
    }

    public void deleteById(Long id) {
        auditoriaRepository.deleteById(id);
    }

    public List<Auditoria> findByEntidadAfectadaAndEntidadId(String entidadAfectada, Long entidadId) {
        return auditoriaRepository.findByEntidadAfectadaAndEntidadId(entidadAfectada, entidadId);
    }

    public List<Auditoria> findByUsuario(String usuario) {
        return auditoriaRepository.findByUsuario(usuario);
    }
}
