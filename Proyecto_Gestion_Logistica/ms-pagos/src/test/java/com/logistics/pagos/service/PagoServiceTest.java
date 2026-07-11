package com.logistics.pagos.service;

import com.logistics.pagos.model.Pago;
import com.logistics.pagos.model.EstadoPago;
import com.logistics.pagos.repository.PagoRepository;
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

class PagoServiceTest {

    @Mock
    private PagoRepository pagoRepository;

    @InjectMocks
    private PagoService pagoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        Pago p1 = new Pago();
        p1.setMonto(100.0);
        Pago p2 = new Pago();
        p2.setMonto(200.0);

        when(pagoRepository.findAll()).thenReturn(Arrays.asList(p1, p2));

        List<Pago> pagos = pagoService.findAll();
        assertEquals(2, pagos.size());
        verify(pagoRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        Pago pago = new Pago();
        pago.setId(1L);
        pago.setMonto(150.0);

        when(pagoRepository.findById(1L)).thenReturn(Optional.of(pago));

        Pago resultado = pagoService.findById(1L);
        assertNotNull(resultado);
        assertEquals(150.0, resultado.getMonto());
    }

    @Test
    void testFindByIdNotFound() {
        when(pagoRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> pagoService.findById(99L));
    }

    @Test
    void testSave() {
        Pago pago = new Pago();
        pago.setMonto(500.0);

        when(pagoRepository.save(any(Pago.class))).thenReturn(pago);

        Pago resultado = pagoService.save(pago);
        assertNotNull(resultado);
        assertEquals(500.0, resultado.getMonto());
    }

    @Test
    void testDeleteById() {
        doNothing().when(pagoRepository).deleteById(1L);
        pagoService.deleteById(1L);
        verify(pagoRepository, times(1)).deleteById(1L);
    }
}
