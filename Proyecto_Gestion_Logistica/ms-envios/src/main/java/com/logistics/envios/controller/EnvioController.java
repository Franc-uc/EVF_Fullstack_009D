package com.logistics.envios.controller;

import com.logistics.envios.model.Envio;
import com.logistics.envios.dto.EnvioDTO;
import com.logistics.common.exception.ResourceNotFoundException;
import com.logistics.envios.service.EnvioService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/envios")
public class EnvioController {

    private final EnvioService envioService;

    public EnvioController(EnvioService envioService) {
        this.envioService = envioService;
    }

    @GetMapping
    public ResponseEntity<List<Envio>> getAllEnvios() {
        return ResponseEntity.ok(envioService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Envio> getEnvioById(@PathVariable Long id) {
        Envio envio = envioService.findById(id);
        return ResponseEntity.ok(envio);
    }

    @PostMapping
    public ResponseEntity<Envio> createEnvio(@Valid @RequestBody EnvioDTO envioDTO) {
        Envio envio = new Envio();
        envio.setPedidoId(envioDTO.getPedidoId());
        envio.setDireccionEnvio(envioDTO.getDireccionEnvio());
        envio.setCiudad(envioDTO.getCiudad());
        envio.setCodigoPostal(envioDTO.getCodigoPostal());
        envio.setEstado(envioDTO.getEstado());
        return ResponseEntity.status(HttpStatus.CREATED).body(envioService.save(envio));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Envio> updateEnvio(@PathVariable Long id, @Valid @RequestBody EnvioDTO envioDTO) {
        Envio existingEnvio = envioService.findById(id);
        existingEnvio.setPedidoId(envioDTO.getPedidoId());
        existingEnvio.setDireccionEnvio(envioDTO.getDireccionEnvio());
        existingEnvio.setCiudad(envioDTO.getCiudad());
        existingEnvio.setCodigoPostal(envioDTO.getCodigoPostal());
        existingEnvio.setEstado(envioDTO.getEstado());
        return ResponseEntity.ok(envioService.save(existingEnvio));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEnvio(@PathVariable Long id) {
        envioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
