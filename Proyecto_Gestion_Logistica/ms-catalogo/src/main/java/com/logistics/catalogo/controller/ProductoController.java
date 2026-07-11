package com.logistics.catalogo.controller;

import com.logistics.catalogo.model.Producto;
import com.logistics.catalogo.dto.ProductoDTO;
import com.logistics.catalogo.model.Categoria;
import com.logistics.catalogo.repository.CategoriaRepository;
import com.logistics.common.exception.ResourceNotFoundException;
import com.logistics.catalogo.service.ProductoService;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    private static final Logger logger = LoggerFactory.getLogger(ProductoController.class);

    private CategoriaRepository categoriaRepository;
    private ProductoService productoService;

    public ProductoController(CategoriaRepository categoriaRepository, ProductoService productoService) {
        this.categoriaRepository = categoriaRepository;
        this.productoService = productoService;
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<Producto>>> getAllProductos() {
        logger.info("Solicitud para obtener todos los productos");
        List<Producto> productos = productoService.findAll();

        List<EntityModel<Producto>> productosModel = productos.stream()
                .map(producto -> EntityModel.of(producto,
                        linkTo(methodOn(ProductoController.class).getProductoById(producto.getId())).withSelfRel(),
                        linkTo(methodOn(ProductoController.class).getAllProductos()).withRel("productos")))
                .collect(Collectors.toList());

        CollectionModel<EntityModel<Producto>> collectionModel = CollectionModel.of(productosModel,
                linkTo(methodOn(ProductoController.class).getAllProductos()).withSelfRel());

        logger.info("Se encontraron {} productos", productos.size());
        return ResponseEntity.ok(collectionModel);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Producto>> getProductoById(@PathVariable Long id) {
        logger.info("Solicitud para obtener producto con ID: {}", id);
        Producto producto = productoService.findById(id);

        EntityModel<Producto> productoModel = EntityModel.of(producto,
                linkTo(methodOn(ProductoController.class).getProductoById(id)).withSelfRel(),
                linkTo(methodOn(ProductoController.class).getAllProductos()).withRel("productos"));

        logger.info("Producto encontrado: {}", producto.getNombre());
        return ResponseEntity.ok(productoModel);
    }

    @PostMapping
    public ResponseEntity<EntityModel<Producto>> createProducto(@Valid @RequestBody ProductoDTO productoDTO) {
        logger.info("Solicitud para crear un nuevo producto: {}", productoDTO.getNombre());

        Categoria categoria = categoriaRepository.findById(productoDTO.getCategoriaId())
                .orElseThrow(() -> {
                    logger.error("Error al crear producto: Categoría {} no encontrada", productoDTO.getCategoriaId());
                    return new ResourceNotFoundException("Categoría no encontrada con id: " + productoDTO.getCategoriaId());
                });

        Producto producto = new Producto();
        producto.setNombre(productoDTO.getNombre());
        producto.setDescripcion(productoDTO.getDescripcion());
        producto.setPrecio(productoDTO.getPrecio());
        producto.setCategoria(categoria);

        Producto savedProducto = productoService.save(producto);

        EntityModel<Producto> productoModel = EntityModel.of(savedProducto,
                linkTo(methodOn(ProductoController.class).getProductoById(savedProducto.getId())).withSelfRel(),
                linkTo(methodOn(ProductoController.class).getAllProductos()).withRel("productos"));

        logger.info("Producto creado exitosamente con ID: {}", savedProducto.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(productoModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Producto>> updateProducto(@PathVariable Long id, @Valid @RequestBody ProductoDTO productoDTO) {
        logger.info("Solicitud para actualizar producto con ID: {}", id);
        Producto existingProducto = productoService.findById(id);

        Categoria categoria = categoriaRepository.findById(productoDTO.getCategoriaId())
                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada con id: " + productoDTO.getCategoriaId()));

        existingProducto.setNombre(productoDTO.getNombre());
        existingProducto.setDescripcion(productoDTO.getDescripcion());
        existingProducto.setPrecio(productoDTO.getPrecio());
        existingProducto.setCategoria(categoria);

        Producto updatedProducto = productoService.save(existingProducto);

        EntityModel<Producto> productoModel = EntityModel.of(updatedProducto,
                linkTo(methodOn(ProductoController.class).getProductoById(id)).withSelfRel(),
                linkTo(methodOn(ProductoController.class).getAllProductos()).withRel("productos"));

        logger.info("Producto con ID: {} actualizado exitosamente", id);
        return ResponseEntity.ok(productoModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProducto(@PathVariable Long id) {
        logger.info("Solicitud para eliminar producto con ID: {}", id);
        productoService.deleteById(id);
        logger.info("Producto con ID: {} eliminado exitosamente", id);
        return ResponseEntity.noContent().build();
    }
}
