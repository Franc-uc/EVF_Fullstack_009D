package com.logistics.envios.service;

import com.logistics.envios.model.Envio;
import com.logistics.envios.repository.EnvioRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import com.logistics.common.exception.ResourceNotFoundException;

@Service
public class EnvioService {

    private EnvioRepository envioRepository;

    public EnvioService(EnvioRepository envioRepository) {
        this.envioRepository = envioRepository;
    }

    public List<Envio> findAll() {
        return envioRepository.findAll();
    }

    public Envio findById(Long id) {
        return envioRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Envío no encontrado con id: " + id));
    }

    public Envio save(Envio envio) {
        return envioRepository.save(envio);
    }

    public void deleteById(Long id) {
        envioRepository.deleteById(id);
    }

    public Envio updateEnvio(Long id, Envio envioDetails) {
        Envio envio = envioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Envío no encontrado con id: " + id));

        envio.setPedidoId(envioDetails.getPedidoId());
        envio.setDireccionEnvio(envioDetails.getDireccionEnvio());
        envio.setCiudad(envioDetails.getCiudad());
        envio.setCodigoPostal(envioDetails.getCodigoPostal());
        envio.setEstado(envioDetails.getEstado());
        envio.setFechaActualizacion(LocalDateTime.now());

        return envioRepository.save(envio);
    }
}
