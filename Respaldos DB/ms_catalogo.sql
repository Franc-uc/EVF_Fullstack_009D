SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

CREATE TABLE `productos` (
  `id` bigint(20) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `descripcion` text DEFAULT NULL,
  `precio` double NOT NULL,
  `categoria_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO `productos` (`id`, `nombre`, `descripcion`, `precio`, `categoria_id`) VALUES
(1, 'Audífonos Gamer HyperX', 'Cancelación de ruido y micrófono certificado', 89990, 3),
(2, 'Teclado Mecánico Razer', 'Switches ópticos lineales y retroiluminación RGB', 59990, 3);

ALTER TABLE `productos`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `productos`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;