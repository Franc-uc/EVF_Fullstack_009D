package com.logistics.proveedores.service;

import com.logistics.proveedores.model.Proveedor;
import com.logistics.proveedores.repository.ProveedorRepository;
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

class ProveedorServiceTest {

    @Mock
    private ProveedorRepository proveedorRepository;

    @InjectMocks
    private ProveedorService proveedorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        Proveedor p1 = new Proveedor();
        Proveedor p2 = new Proveedor();

        when(proveedorRepository.findAll()).thenReturn(Arrays.asList(p1, p2));

        List<Proveedor> proveedores = proveedorService.findAll();
        assertEquals(2, proveedores.size());
        verify(proveedorRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        Proveedor prov = new Proveedor();
        prov.setId(1L);

        when(proveedorRepository.findById(1L)).thenReturn(Optional.of(prov));

        Proveedor resultado = proveedorService.findById(1L);
        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
    }

    @Test
    void testFindByIdNotFound() {
        when(proveedorRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> proveedorService.findById(99L));
    }

    @Test
    void testSave() {
        Proveedor prov = new Proveedor();

        when(proveedorRepository.save(any(Proveedor.class))).thenReturn(prov);

        Proveedor resultado = proveedorService.save(prov);
        assertNotNull(resultado);
    }

    @Test
    void testDeleteById() {
        doNothing().when(proveedorRepository).deleteById(1L);
        proveedorService.deleteById(1L);
        verify(proveedorRepository, times(1)).deleteById(1L);
    }
}
