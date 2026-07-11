package com.logistics.ordenes.controller;

import com.logistics.ordenes.model.Pedido;
import com.logistics.ordenes.model.DetallePedido;
import com.logistics.ordenes.dto.PedidoDTO;
import com.logistics.ordenes.service.PedidoService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping
    public ResponseEntity<List<Pedido>> getAllPedidos() {
        return ResponseEntity.ok(pedidoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> getPedidoById(@PathVariable Long id) {
        Pedido pedido = pedidoService.findById(id);
        return ResponseEntity.ok(pedido);
    }

    @PostMapping
    public ResponseEntity<Pedido> createPedido(@Valid @RequestBody PedidoDTO pedidoDTO) {
        Pedido pedido = new Pedido();
        pedido.setEstado(pedidoDTO.getEstado());
        if (pedidoDTO.getItems() != null) {
            pedidoDTO.getItems().forEach(itemDTO -> {
                DetallePedido item = new DetallePedido();
                item.setProductoId(itemDTO.getProductoId());
                item.setCantidad(itemDTO.getCantidad());
                item.setPrecioUnitario(itemDTO.getPrecioUnitario());
                item.setPedido(pedido);
                pedido.getItems().add(item);
            });
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoService.save(pedido));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pedido> updatePedido(@PathVariable Long id, @Valid @RequestBody PedidoDTO pedidoDTO) {
        Pedido existingPedido = pedidoService.findById(id);
        existingPedido.setEstado(pedidoDTO.getEstado());
        return ResponseEntity.ok(pedidoService.save(existingPedido));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePedido(@PathVariable Long id) {
        pedidoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
