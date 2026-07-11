package com.logistics.usuarios.config;

import com.github.javafaker.Faker;
import com.logistics.usuarios.model.Usuario;
import com.logistics.usuarios.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Locale;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(UsuarioRepository usuarioRepository) {
        return args -> {
            if (usuarioRepository.count() == 0) {
                Faker faker = new Faker(new Locale("es"));
                for (int i = 0; i < 15; i++) {
                    Usuario usuario = new Usuario();
                    usuario.setUsername(faker.name().username());
                    usuario.setEmail(faker.internet().emailAddress());
                    usuario.setNombre(faker.name().firstName());
                    usuario.setApellido(faker.name().lastName());
                    usuario.setPassword("password123");
                    usuarioRepository.save(usuario);
                }
                System.out.println("15 Usuarios generados con Faker exitosamente");
            }
        };
    }
}
