package com.logistics.ordenes.service;

import com.logistics.ordenes.model.Pedido;
import com.logistics.ordenes.model.DetallePedido;
import com.logistics.ordenes.client.CatalogoClient;
import com.logistics.ordenes.client.StockClient;
import com.logistics.ordenes.dto.external.ProductoDTO;
import com.logistics.ordenes.dto.external.InventarioDTO;
import com.logistics.ordenes.repository.PedidoRepository;
import com.logistics.common.exception.ResourceNotFoundException;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

    private CatalogoClient catalogoClient;

    public PedidoService(CatalogoClient catalogoClient) {
        this.catalogoClient = catalogoClient;
    }

    private StockClient stockClient;

    public PedidoService(StockClient stockClient) {
        this.stockClient = stockClient;
    }

    private PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public List<Pedido> findAll() {
        return pedidoRepository.findAll();
    }

    public Pedido findById(Long id) {
        return pedidoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Pedido no encontrado con id: " + id));
    }

    public Pedido save(Pedido pedido) {
        for (DetallePedido item : pedido.getItems()) {
            ProductoDTO producto = catalogoClient.getProductoById(item.getProductoId());
            if (producto == null) {
                throw new ResourceNotFoundException("Producto no encontrado con id: " + item.getProductoId());
            }
            InventarioDTO inventario = stockClient.getInventarioByProductoId(item.getProductoId());
            if (inventario == null || inventario.getCantidad() < item.getCantidad()) {
                throw new RuntimeException("Stock insuficiente para el producto: " + item.getProductoId());
            }
            stockClient.updateStock(item.getProductoId(), -item.getCantidad());
        }
        return pedidoRepository.save(pedido);
    }

    public void deleteById(Long id) {
        pedidoRepository.deleteById(id);
    }
}
