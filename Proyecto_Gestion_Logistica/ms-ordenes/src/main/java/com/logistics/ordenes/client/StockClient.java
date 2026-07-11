package com.logistics.ordenes.client;

import com.logistics.ordenes.dto.external.InventarioDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "ms-stock", url = "http://localhost:8082/inventarios") // Asumiendo que ms-stock corre en 8082
public interface StockClient {

    @GetMapping("/producto/{productoId}")
    InventarioDTO getInventarioByProductoId(@PathVariable("productoId") Long productoId);

    @PostMapping("/update-stock")
    InventarioDTO updateStock(@RequestParam("productoId") Long productoId, @RequestParam("cantidadCambio") Integer cantidadCambio);
}
