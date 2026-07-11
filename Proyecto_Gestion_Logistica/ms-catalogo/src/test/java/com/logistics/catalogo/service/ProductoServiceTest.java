package com.logistics.catalogo.service;

import com.logistics.catalogo.model.Producto;
import com.logistics.catalogo.repository.ProductoRepository;
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

class ProductoServiceTest {

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ProductoService productoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        Producto p1 = new Producto();
        p1.setNombre("Producto 1");
        Producto p2 = new Producto();
        p2.setNombre("Producto 2");

        when(productoRepository.findAll()).thenReturn(Arrays.asList(p1, p2));

        List<Producto> productos = productoService.findAll();
        assertEquals(2, productos.size());
        verify(productoRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        Producto p = new Producto();
        p.setId(1L);
        p.setNombre("Test");

        when(productoRepository.findById(1L)).thenReturn(Optional.of(p));

        Producto resultado = productoService.findById(1L);
        assertNotNull(resultado);
        assertEquals("Test", resultado.getNombre());
    }

    @Test
    void testFindByIdNotFound() {
        when(productoRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> productoService.findById(99L));
    }

    @Test
    void testSave() {
        Producto p = new Producto();
        p.setNombre("Nuevo Producto");

        when(productoRepository.save(any(Producto.class))).thenReturn(p);

        Producto resultado = productoService.save(p);
        assertNotNull(resultado);
        assertEquals("Nuevo Producto", resultado.getNombre());
    }

    @Test
    void testDeleteById() {
        doNothing().when(productoRepository).deleteById(1L);
        productoService.deleteById(1L);
        verify(productoRepository, times(1)).deleteById(1L);
    }
}
