package com.logistics.proveedores.controller;

import com.logistics.proveedores.model.Proveedor;
import com.logistics.proveedores.service.ProveedorService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/proveedores")
public class ProveedorController {

    private ProveedorService proveedorService;

    public ProveedorController(ProveedorService proveedorService) {
        this.proveedorService = proveedorService;
    }

    @GetMapping
    public ResponseEntity<List<Proveedor>> getAllProveedores() {
        return ResponseEntity.ok(proveedorService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Proveedor> getProveedorById(@PathVariable Long id) {
        Proveedor proveedor = proveedorService.findById(id);
        return ResponseEntity.ok(proveedor);
    }

    @PostMapping
    public ResponseEntity<Proveedor> createProveedor(@Valid @RequestBody Proveedor proveedor) {
        return ResponseEntity.status(HttpStatus.CREATED).body(proveedorService.save(proveedor));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Proveedor> updateProveedor(@PathVariable Long id, @Valid @RequestBody Proveedor proveedor) {
        Proveedor existingProveedor = proveedorService.findById(id);
        existingProveedor.setNombre(proveedor.getNombre());
        existingProveedor.setDireccion(proveedor.getDireccion());
        existingProveedor.setTelefono(proveedor.getTelefono());
        existingProveedor.setEmail(proveedor.getEmail());
        return ResponseEntity.ok(proveedorService.save(existingProveedor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProveedor(@PathVariable Long id) {
        proveedorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
