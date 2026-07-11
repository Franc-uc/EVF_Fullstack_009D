package com.logistics.stock.service;

import com.logistics.stock.model.Inventario;
import com.logistics.stock.repository.InventarioRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import com.logistics.common.exception.ResourceNotFoundException;

@Service
public class InventarioService {

    private InventarioRepository inventarioRepository;

    public InventarioService(InventarioRepository inventarioRepository) {
        this.inventarioRepository = inventarioRepository;
    }

    public List<Inventario> findAll() {
        return inventarioRepository.findAll();
    }

    public Inventario findById(Long id) {
        return inventarioRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Inventario no encontrado con id: " + id));
    }

    public Inventario findByProductoId(Long productoId) {
        return inventarioRepository.findByProductoId(productoId).orElseThrow(() -> new ResourceNotFoundException("Inventario no encontrado para el producto: " + productoId));
    }

    public Inventario save(Inventario inventario) {
        return inventarioRepository.save(inventario);
    }

    public void deleteById(Long id) {
        inventarioRepository.deleteById(id);
    }

    public Inventario updateStock(Long productoId, Integer cantidadCambio) {
        Inventario inventario = inventarioRepository.findByProductoId(productoId)
                .orElseThrow(() -> new ResourceNotFoundException("Inventario no encontrado para el producto: " + productoId));
        inventario.setCantidad(inventario.getCantidad() + cantidadCambio);
        if (inventario.getCantidad() < 0) {
            throw new RuntimeException("Stock insuficiente para el producto: " + productoId);
        }
        return inventarioRepository.save(inventario);
    }
}
