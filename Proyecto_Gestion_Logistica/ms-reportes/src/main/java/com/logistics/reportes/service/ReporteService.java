package com.logistics.reportes.service;

import com.logistics.reportes.model.Reporte;
import com.logistics.reportes.repository.ReporteRepository;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import com.logistics.common.exception.ResourceNotFoundException;

@Service
public class ReporteService {

    private static final Logger logger = LoggerFactory.getLogger(ReporteService.class);

    private ReporteRepository reporteRepository;

    public ReporteService(ReporteRepository reporteRepository) {
        this.reporteRepository = reporteRepository;
    }

    private WebClient.Builder webClientBuilder;

    public ReporteService(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    public Object getExternalData(String serviceName, String endpoint) {
        logger.info("Iniciando llamada WebClient al servicio: {} en el endpoint: {}", serviceName, endpoint);
        return webClientBuilder.build()
                .get()
                .uri("http://" + serviceName + endpoint)
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }

    public List<Reporte> findAll() {
        return reporteRepository.findAll();
    }

    public Reporte findById(Long id) {
        return reporteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Reporte no encontrado. ID: " + id));
    }

    public Reporte save(Reporte reporte) {
        return reporteRepository.save(reporte);
    }

    public void deleteById(Long id) {
        reporteRepository.deleteById(id);
    }

    public List<Reporte> findByTipoReporte(String tipoReporte) {
        return reporteRepository.findByTipoReporte(tipoReporte);
    }
}
