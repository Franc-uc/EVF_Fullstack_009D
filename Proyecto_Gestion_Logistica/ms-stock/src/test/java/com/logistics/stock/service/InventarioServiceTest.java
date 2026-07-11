package com.logistics.stock.service;

import com.logistics.stock.model.Inventario;
import com.logistics.stock.repository.InventarioRepository;
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

class InventarioServiceTest {

    @Mock
    private InventarioRepository inventarioRepository;

    @InjectMocks
    private InventarioService inventarioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        Inventario i1 = new Inventario();
        i1.setCantidad(10);
        Inventario i2 = new Inventario();
        i2.setCantidad(20);

        when(inventarioRepository.findAll()).thenReturn(Arrays.asList(i1, i2));

        List<Inventario> inventarios = inventarioService.findAll();
        assertEquals(2, inventarios.size());
        verify(inventarioRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        Inventario inv = new Inventario();
        inv.setId(1L);
        inv.setCantidad(50);

        when(inventarioRepository.findById(1L)).thenReturn(Optional.of(inv));

        Inventario resultado = inventarioService.findById(1L);
        assertNotNull(resultado);
        assertEquals(50, resultado.getCantidad());
    }

    @Test
    void testFindByIdNotFound() {
        when(inventarioRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> inventarioService.findById(99L));
    }

    @Test
    void testUpdateStock() {
        Inventario inv = new Inventario();
        inv.setId(1L);
        inv.setProductoId(10L);
        inv.setCantidad(50);

        when(inventarioRepository.findByProductoId(10L)).thenReturn(Optional.of(inv));
        when(inventarioRepository.save(any(Inventario.class))).thenReturn(inv);

        Inventario resultado = inventarioService.updateStock(10L, -5);
        assertEquals(45, resultado.getCantidad());
    }

    @Test
    void testUpdateStockInsuficiente() {
        Inventario inv = new Inventario();
        inv.setId(1L);
        inv.setProductoId(10L);
        inv.setCantidad(3);

        when(inventarioRepository.findByProductoId(10L)).thenReturn(Optional.of(inv));

        assertThrows(RuntimeException.class, () -> inventarioService.updateStock(10L, -10));
    }
}
