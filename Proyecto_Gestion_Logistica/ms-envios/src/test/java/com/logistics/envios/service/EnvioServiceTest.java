package com.logistics.envios.service;

import com.logistics.envios.model.Envio;
import com.logistics.envios.model.EstadoEnvio;
import com.logistics.envios.repository.EnvioRepository;
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

class EnvioServiceTest {

    @Mock
    private EnvioRepository envioRepository;

    @InjectMocks
    private EnvioService envioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        Envio e1 = new Envio();
        e1.setDireccionEnvio("Calle 1");
        Envio e2 = new Envio();
        e2.setDireccionEnvio("Calle 2");

        when(envioRepository.findAll()).thenReturn(Arrays.asList(e1, e2));

        List<Envio> envios = envioService.findAll();
        assertEquals(2, envios.size());
        verify(envioRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        Envio envio = new Envio();
        envio.setId(1L);
        envio.setDireccionEnvio("Av. Principal 123");

        when(envioRepository.findById(1L)).thenReturn(Optional.of(envio));

        Envio resultado = envioService.findById(1L);
        assertNotNull(resultado);
        assertEquals("Av. Principal 123", resultado.getDireccionEnvio());
    }

    @Test
    void testFindByIdNotFound() {
        when(envioRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> envioService.findById(99L));
    }

    @Test
    void testSave() {
        Envio envio = new Envio();
        envio.setDireccionEnvio("Calle Nueva 456");

        when(envioRepository.save(any(Envio.class))).thenReturn(envio);

        Envio resultado = envioService.save(envio);
        assertNotNull(resultado);
        assertEquals("Calle Nueva 456", resultado.getDireccionEnvio());
    }

    @Test
    void testDeleteById() {
        doNothing().when(envioRepository).deleteById(1L);
        envioService.deleteById(1L);
        verify(envioRepository, times(1)).deleteById(1L);
    }
}
