# Proyecto Final: Sistema de Gestión Logística (Microservicios)

Este proyecto es la solución desarrollada para el Examen Final Transversal (EFT). Consiste en una arquitectura de microservicios para gestionar la logística de una empresa, cubriendo desde el catálogo de productos hasta el despacho y auditoría.

# Integrantes del Equipo
* [Fabio Lobos]
* [Franco Pareja]
* [Matias Valenzuela]
* [Paulina Yupanqui]

# Estructura del Sistema

El sistema se divide en los siguientes componentes:

1. Infraestructura:
   - Eureka Server: Registro y descubrimiento de servicios (Puerto 8761).
   - API Gateway: Punto de entrada único (Puerto 8080).
   - Common Lib: Librería compartida para manejo de errores y DTOs comunes.

2. Microservicios de Negocio:

   - Catálogo: Productos y categorías (8081).

   - Stock: Control de inventario (8082).

   - Órdenes: Gestión de pedidos y carritos (8083).

   - Usuarios: Registro y perfiles (8084).

   - Envíos: Despacho y seguimiento (8085).

   - Pagos: Transacciones financieras (8086).

   - Notificaciones: Envío de avisos (8087).

   - Auditoría: Registro de eventos del sistema (8088).

   - Proveedores: Gestión de contactos externos (8089).

   - Reportes: Estadísticas y consultas (8090).

# Tecnologías principales
- Java 17 / Spring Boot 3.2.5
- Spring Cloud (Eureka, Gateway, OpenFeign)
- MySQL 8.0
- Docker y Docker Compose
- Swagger para la documentación de las APIs

# Cómo ejecutar el proyecto
Para levantar todo el ecosistema, se requiere tener Docker instalado. Ejecutar en la raíz:

```bash
docker-compose up -d --build
```

Una vez arriba, se puede acceder a:
- Eureka: http://localhost:8761
- Gateway: http://localhost:8080
- Swagger (ejemplo catálogo): http://localhost:8081/swagger-ui/index.html

# Pruebas
Se incluyeron pruebas unitarias para validar la lógica de negocio. Se pueden ejecutar con:
```bash
mvn test
```
