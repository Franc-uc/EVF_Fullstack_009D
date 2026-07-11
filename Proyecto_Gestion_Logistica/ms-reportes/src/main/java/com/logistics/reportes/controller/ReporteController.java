package com.logistics.reportes.controller;

import com.logistics.reportes.model.Reporte;
import com.logistics.reportes.service.ReporteService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/reportes")
public class ReporteController {

    private ReporteService reporteService;

    public ReporteController(ReporteService reporteService) {
        this.reporteService = reporteService;
    }

    @GetMapping
    public ResponseEntity<List<Reporte>> getAllReportes() {
        return ResponseEntity.ok(reporteService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reporte> getReporteById(@PathVariable Long id) {
        Reporte reporte = reporteService.findById(id);
        return ResponseEntity.ok(reporte);
    }

    @PostMapping
    public ResponseEntity<Reporte> createReporte(@Valid @RequestBody Reporte reporte) {
        return ResponseEntity.status(HttpStatus.CREATED).body(reporteService.save(reporte));
    }

    @GetMapping("/tipo/{tipoReporte}")
    public ResponseEntity<List<Reporte>> getReportesByTipo(@PathVariable String tipoReporte) {
        return ResponseEntity.ok(reporteService.findByTipoReporte(tipoReporte));
    }

    @GetMapping("/external-data")
    public ResponseEntity<Object> getExternalData(@RequestParam String service, @RequestParam String endpoint) {
        return ResponseEntity.ok(reporteService.getExternalData(service, endpoint));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReporte(@PathVariable Long id) {
        reporteService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
