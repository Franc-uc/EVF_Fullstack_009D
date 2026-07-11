package com.logistics.catalogo.config;

import com.github.javafaker.Faker;
import com.logistics.catalogo.model.Categoria;
import com.logistics.catalogo.model.Producto;
import com.logistics.catalogo.repository.CategoriaRepository;
import com.logistics.catalogo.repository.ProductoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Locale;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(ProductoRepository productoRepository, CategoriaRepository categoriaRepository) {
        return args -> {
            if (categoriaRepository.count() == 0) {
                Faker faker = new Faker(new Locale("es"));
                
                Categoria cat1 = new Categoria();
                cat1.setNombre("Electrónica");
                cat1.setDescripcion("Dispositivos electrónicos y gadgets");
                categoriaRepository.save(cat1);

                Categoria cat2 = new Categoria();
                cat2.setNombre("Hogar");
                cat2.setDescripcion("Artículos para el hogar");
                categoriaRepository.save(cat2);

                for (int i = 0; i < 10; i++) {
                    Producto p = new Producto();
                    p.setNombre(faker.commerce().productName());
                    p.setDescripcion(faker.lorem().sentence());
                    p.setPrecio(Double.parseDouble(faker.commerce().price().replace(",", ".")));
                    p.setCategoria(i % 2 == 0 ? cat1 : cat2);
                    productoRepository.save(p);
                }
            }
        };
    }
}
