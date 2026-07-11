package com.logistics.stock.config;

import com.github.javafaker.Faker;
import com.logistics.stock.model.Inventario;
import com.logistics.stock.repository.InventarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Locale;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(InventarioRepository inventarioRepository) {
        return args -> {
            if (inventarioRepository.count() == 0) {
                Faker faker = new Faker(new Locale("es"));
                for (int i = 1; i <= 10; i++) {
                    Inventario inventario = new Inventario();
                    inventario.setProductoId((long) i);
                    inventario.setCantidad(faker.number().numberBetween(10, 500));
                    inventario.setUbicacion("Bodega " + faker.address().buildingNumber());
                    inventarioRepository.save(inventario);
                }
                System.out.println("10 registros de Inventario generados con Faker exitosamente");
            }
        };
    }
}
