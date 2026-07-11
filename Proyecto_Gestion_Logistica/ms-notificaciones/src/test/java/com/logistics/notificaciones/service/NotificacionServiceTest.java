package com.logistics.notificaciones.service;

import com.logistics.notificaciones.model.Notificacion;
import com.logistics.notificaciones.repository.NotificacionRepository;
import com.logistics.common.exception.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class NotificacionServiceTest {

    @Mock
    private NotificacionRepository notificacionRepository;

    @InjectMocks
    private NotificacionService notificacionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        Notificacion n1 = new Notificacion();
        Notificacion n2 = new Notificacion();

        when(notificacionRepository.findAll()).thenReturn(Arrays.asList(n1, n2));

        List<Notificacion> notificaciones = notificacionService.findAll();
        assertEquals(2, notificaciones.size());
        verify(notificacionRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        Notificacion notif = new Notificacion();
        notif.setId(1L);

        when(notificacionRepository.findById(1L)).thenReturn(Optional.of(notif));

        Notificacion resultado = notificacionService.findById(1L);
        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
    }

    @Test
    void testFindByIdNotFound() {
        when(notificacionRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> notificacionService.findById(99L));
    }

    @Test
    void testMarkAsRead() {
        Notificacion notif = new Notificacion();
        notif.setId(1L);
        notif.setLeida(false);

        when(notificacionRepository.findById(1L)).thenReturn(Optional.of(notif));
        when(notificacionRepository.save(any(Notificacion.class))).thenReturn(notif);

        Notificacion resultado = notificacionService.markAsRead(1L);
        assertTrue(resultado.getLeida());
    }

    @Test
    void testDeleteById() {
        doNothing().when(notificacionRepository).deleteById(1L);
        notificacionService.deleteById(1L);
        verify(notificacionRepository, times(1)).deleteById(1L);
    }
}
