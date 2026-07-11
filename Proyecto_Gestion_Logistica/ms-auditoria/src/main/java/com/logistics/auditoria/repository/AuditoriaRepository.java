package com.logistics.auditoria.repository;

import com.logistics.auditoria.model.Auditoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuditoriaRepository extends JpaRepository<Auditoria, Long> {
    List<Auditoria> findByEntidadAfectadaAndEntidadId(String entidadAfectada, Long entidadId);
    List<Auditoria> findByUsuario(String usuario);
}
