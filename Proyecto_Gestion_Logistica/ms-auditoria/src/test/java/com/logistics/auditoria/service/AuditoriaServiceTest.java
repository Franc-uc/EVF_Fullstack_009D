package com.logistics.auditoria.service;

import com.logistics.auditoria.model.Auditoria;
import com.logistics.auditoria.repository.AuditoriaRepository;
import com.logistics.common.exception.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuditoriaServiceTest {

    @Mock
    private AuditoriaRepository auditoriaRepository;

    @InjectMocks
    private AuditoriaService auditoriaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        Auditoria a1 = new Auditoria();
        Auditoria a2 = new Auditoria();

        when(auditoriaRepository.findAll()).thenReturn(Arrays.asList(a1, a2));

        List<Auditoria> auditorias = auditoriaService.findAll();
        assertEquals(2, auditorias.size());
        verify(auditoriaRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        Auditoria aud = new Auditoria();
        aud.setId(1L);

        when(auditoriaRepository.findById(1L)).thenReturn(Optional.of(aud));

        Auditoria resultado = auditoriaService.findById(1L);
        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
    }

    @Test
    void testFindByIdNotFound() {
        when(auditoriaRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> auditoriaService.findById(99L));
    }

    @Test
    void testSave() {
        Auditoria aud = new Auditoria();
        aud.setUsuario("admin");

        when(auditoriaRepository.save(any(Auditoria.class))).thenReturn(aud);

        Auditoria resultado = auditoriaService.save(aud);
        assertNotNull(resultado);
        assertEquals("admin", resultado.getUsuario());
    }

    @Test
    void testDeleteById() {
        doNothing().when(auditoriaRepository).deleteById(1L);
        auditoriaService.deleteById(1L);
        verify(auditoriaRepository, times(1)).deleteById(1L);
    }
}