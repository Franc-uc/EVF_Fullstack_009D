package com.logistics.proveedores.config;

import com.github.javafaker.Faker;
import com.logistics.proveedores.model.Proveedor;
import com.logistics.proveedores.repository.ProveedorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Locale;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(ProveedorRepository proveedorRepository) {
        return args -> {
            if (proveedorRepository.count() == 0) {
                Faker faker = new Faker(new Locale("es"));
                for (int i = 0; i < 10; i++) {
                    Proveedor proveedor = new Proveedor();
                    proveedor.setNombre(faker.company().name());
                    proveedor.setContacto(faker.name().fullName());
                    proveedor.setEmail(faker.internet().emailAddress());
                    proveedor.setTelefono(faker.phoneNumber().phoneNumber());
                    proveedor.setDireccion(faker.address().fullAddress());
                    proveedorRepository.save(proveedor);
                }
                System.out.println("10 Proveedores generados con Faker exitosamente");
            }
        };
    }
}
