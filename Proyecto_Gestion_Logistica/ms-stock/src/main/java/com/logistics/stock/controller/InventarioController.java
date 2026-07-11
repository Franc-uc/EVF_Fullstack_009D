package com.logistics.stock.controller;

import com.logistics.stock.model.Inventario;
import com.logistics.stock.dto.InventarioDTO;
import com.logistics.common.exception.ResourceNotFoundException;
import com.logistics.stock.service.InventarioService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/inventarios")
public class InventarioController {

    private final InventarioService inventarioService;

    public InventarioController(InventarioService inventarioService) {
        this.inventarioService = inventarioService;
    }

    @GetMapping
    public ResponseEntity<List<Inventario>> getAllInventarios() {
        return ResponseEntity.ok(inventarioService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inventario> getInventarioById(@PathVariable Long id) {
        Inventario inventario = inventarioService.findById(id);
        return ResponseEntity.ok(inventario);
    }

    @PostMapping
    public ResponseEntity<Inventario> createInventario(@Valid @RequestBody InventarioDTO inventarioDTO) {
        Inventario inventario = new Inventario();
        inventario.setProductoId(inventarioDTO.getProductoId());
        inventario.setCantidad(inventarioDTO.getCantidad());
        inventario.setUbicacion(inventarioDTO.getBodegaUbicacion());
        return ResponseEntity.status(HttpStatus.CREATED).body(inventarioService.save(inventario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Inventario> updateInventario(@PathVariable Long id, @Valid @RequestBody InventarioDTO inventarioDTO) {
        Inventario existingInventario = inventarioService.findById(id);
        existingInventario.setProductoId(inventarioDTO.getProductoId());
        existingInventario.setCantidad(inventarioDTO.getCantidad());
        existingInventario.setUbicacion(inventarioDTO.getBodegaUbicacion());
        return ResponseEntity.ok(inventarioService.save(existingInventario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInventario(@PathVariable Long id) {
        inventarioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/update-stock")
    public ResponseEntity<Inventario> updateStock(@RequestParam Long productoId, @RequestParam Integer cantidadCambio) {
        return ResponseEntity.ok(inventarioService.updateStock(productoId, cantidadCambio));
    }
}
