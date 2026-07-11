SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

CREATE TABLE `reportes` (
  `id` bigint(20) NOT NULL,
  `tipo_reporte` varchar(100) NOT NULL,
  `fecha_generacion` datetime NOT NULL,
  `contenido` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO `reportes` (`id`, `tipo_reporte`, `fecha_generacion`, `contenido`) VALUES
(1, 'Resumen diraio de ventas', '2026-06-30 23:30', '{\"total_ventas\": 159980, \"ordenes_procesadas\": 1, \"estado\": \"exitoso\"}');

ALTER TABLE `reportes`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `reportes`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;