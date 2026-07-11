package com.logistics.pagos.service;

import com.logistics.pagos.model.Pago;
import com.logistics.pagos.repository.PagoRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PagoService {

    private PagoRepository pagoRepository;

    public PagoService(PagoRepository pagoRepository) {
        this.pagoRepository = pagoRepository;
    }

    public List<Pago> findAll() {
        return pagoRepository.findAll();
    }

    public Pago findById(Long id) {
        return pagoRepository.findById(id).orElseThrow(() -> new RuntimeException("Pago no encontrado"));
    }

    public Pago save(Pago pago) {
        return pagoRepository.save(pago);
    }

    public void deleteById(Long id) {
        pagoRepository.deleteById(id);
    }

    public Pago updatePago(Long id, Pago pagoDetails) {
        Pago pago = pagoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pago no encontrado con id: " + id));

        pago.setPedidoId(pagoDetails.getPedidoId());
        pago.setMonto(pagoDetails.getMonto());
        pago.setMetodoPago(pagoDetails.getMetodoPago());
        pago.setEstado(pagoDetails.getEstado());
        pago.setFechaPago(LocalDateTime.now());

        return pagoRepository.save(pago);
    }
}
