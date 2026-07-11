package com.logistics.ordenes.client;

import com.logistics.ordenes.dto.external.ProductoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-catalogo", url = "http://localhost:8081/productos") // Asumiendo que ms-catalogo corre en 8081
public interface CatalogoClient {

    @GetMapping("/{id}")
    ProductoDTO getProductoById(@PathVariable("id") Long id);
}
