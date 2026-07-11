package com.logistics.common.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Logistics Management System API")
                        .version("1.0")
                        .description("Documentación de la API para el Sistema de Gestión Logística")
                        .contact(new Contact()
                                .name("Soporte Logística")
                                .email("soporte@logistics.com")));
    }
}
