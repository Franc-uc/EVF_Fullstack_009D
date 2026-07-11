package com.logistics.envios.config;

import com.github.javafaker.Faker;
import com.logistics.envios.model.Envio;
import com.logistics.envios.model.EstadoEnvio;
import com.logistics.envios.repository.EnvioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.Locale;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(EnvioRepository envioRepository) {
        return args -> {
            if (envioRepository.count() == 0) {
                Faker faker = new Faker(new Locale("es"));
                EstadoEnvio[] estados = EstadoEnvio.values();
                for (int i = 0; i < 10; i++) {
                    Envio envio = new Envio();
                    envio.setPedidoId((long) (i + 1));
                    envio.setDireccionEnvio(faker.address().streetAddress());
                    envio.setCiudad(faker.address().city());
                    envio.setCodigoPostal(faker.address().zipCode());
                    envio.setEstado(estados[faker.number().numberBetween(0, estados.length)]);
                    envio.setFechaCreacion(LocalDateTime.now());
                    envio.setFechaActualizacion(LocalDateTime.now());
                    envioRepository.save(envio);
                }
                System.out.println("10 Envíos generados con Faker exitosamente");
            }
        };
    }
}
