package com.logistics.auditoria.controller;

import com.logistics.auditoria.model.Auditoria;
import com.logistics.auditoria.dto.AuditoriaDTO;
import com.logistics.common.exception.ResourceNotFoundException;
import com.logistics.auditoria.service.AuditoriaService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/auditorias")
public class AuditoriaController {

    private final AuditoriaService auditoriaService;

    public AuditoriaController(AuditoriaService auditoriaService) {
        this.auditoriaService = auditoriaService;
    }

    @GetMapping
    public ResponseEntity<List<Auditoria>> getAllAuditorias() {
        return ResponseEntity.ok(auditoriaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Auditoria> getAuditoriaById(@PathVariable Long id) {
        Auditoria auditoria = auditoriaService.findById(id);
        return ResponseEntity.ok(auditoria);
    }

    @PostMapping
    public ResponseEntity<Auditoria> createAuditoria(@Valid @RequestBody AuditoriaDTO auditoriaDTO) {
        Auditoria auditoria = new Auditoria();
        auditoria.setUsuario(auditoriaDTO.getUsuario());
        auditoria.setAccion(auditoriaDTO.getAccion());
        auditoria.setEntidadAfectada(auditoriaDTO.getEntidadAfectada());
        auditoria.setEntidadId(auditoriaDTO.getEntidadId());
        auditoria.setDetalles(auditoriaDTO.getDetalles());
        return ResponseEntity.status(HttpStatus.CREATED).body(auditoriaService.save(auditoria));
    }

    @GetMapping("/entidad/{entidadAfectada}/{entidadId}")
    public ResponseEntity<List<Auditoria>> getAuditoriasByEntidad(@PathVariable String entidadAfectada, @PathVariable Long entidadId) {
        return ResponseEntity.ok(auditoriaService.findByEntidadAfectadaAndEntidadId(entidadAfectada, entidadId));
    }

    @GetMapping("/usuario/{usuario}")
    public ResponseEntity<List<Auditoria>> getAuditoriasByUsuario(@PathVariable String usuario) {
        return ResponseEntity.ok(auditoriaService.findByUsuario(usuario));
    }

}
