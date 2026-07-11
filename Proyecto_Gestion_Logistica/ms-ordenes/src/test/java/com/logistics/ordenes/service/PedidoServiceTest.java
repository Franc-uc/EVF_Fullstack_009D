package com.logistics.ordenes.service;

import com.logistics.ordenes.model.Pedido;
import com.logistics.ordenes.repository.PedidoRepository;
import com.logistics.ordenes.client.CatalogoClient;
import com.logistics.ordenes.client.StockClient;
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

class PedidoServiceTest {

    @Mock
    private PedidoRepository pedidoRepository;

    @Mock
    private CatalogoClient catalogoClient;

    @Mock
    private StockClient stockClient;

    @InjectMocks
    private PedidoService pedidoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        Pedido p1 = new Pedido();
        Pedido p2 = new Pedido();

        when(pedidoRepository.findAll()).thenReturn(Arrays.asList(p1, p2));

        List<Pedido> pedidos = pedidoService.findAll();
        assertEquals(2, pedidos.size());
        verify(pedidoRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        Pedido pedido = new Pedido();
        pedido.setId(1L);

        when(pedidoRepository.findById(1L)).thenReturn(Optional.of(pedido));

        Pedido resultado = pedidoService.findById(1L);
        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
    }

    @Test
    void testFindByIdNotFound() {
        when(pedidoRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> pedidoService.findById(99L));
    }

    @Test
    void testDeleteById() {
        doNothing().when(pedidoRepository).deleteById(1L);
        pedidoService.deleteById(1L);
        verify(pedidoRepository, times(1)).deleteById(1L);
    }
}
