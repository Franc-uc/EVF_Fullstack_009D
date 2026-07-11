package com.logistics.reportes.service;

import com.logistics.reportes.model.Reporte;
import com.logistics.reportes.repository.ReporteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReporteServiceTest {

    @Mock
    private ReporteRepository reporteRepository;

    @InjectMocks
    private ReporteService reporteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        Reporte r1 = new Reporte();
        Reporte r2 = new Reporte();

        when(reporteRepository.findAll()).thenReturn(Arrays.asList(r1, r2));

        List<Reporte> reportes = reporteService.findAll();
        assertEquals(2, reportes.size());
        verify(reporteRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        Reporte reporte = new Reporte();
        reporte.setId(1L);

        when(reporteRepository.findById(1L)).thenReturn(Optional.of(reporte));

        Reporte resultado = reporteService.findById(1L);
        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
    }

    @Test
    void testSave() {
        Reporte reporte = new Reporte();

        when(reporteRepository.save(any(Reporte.class))).thenReturn(reporte);

        Reporte resultado = reporteService.save(reporte);
        assertNotNull(resultado);
    }

    @Test
    void testDeleteById() {
        doNothing().when(reporteRepository).deleteById(1L);
        reporteService.deleteById(1L);
        verify(reporteRepository, times(1)).deleteById(1L);
    }
}
