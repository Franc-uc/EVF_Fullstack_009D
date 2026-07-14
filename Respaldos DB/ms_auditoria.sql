auditoriasSET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

CREATE TABLE `auditorias` (
  `id` bigint(20) NOT NULL,
  `usuario` varchar(50) NOT NULL,
  `accion` varchar(100) NOT NULL,
  `entidad_afectada` varchar(50) DEFAULT NULL,
  `entidad_id` bigint(20) DEFAULT NULL,
  `fecha_accion` datetime NOT NULL,
  `detalles` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO `auditorias` (`id`, `usuario`, `accion`, `entidad_afectada`, `entidad_id`, `fecha_accion`, `detalles`) VALUES
(1, 'admin_sistema', 'CREAR_PRODUCTO', 'Producto', 1, '2026-07-050 14:47', 'Se ingresó al catálogo el producto Audífonos Gamer HyperX');

ALTER TABLE `auditorias`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `auditorias`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;