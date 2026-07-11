SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

CREATE TABLE `inventarios` (
  `id` bigint(20) NOT NULL,
  `producto_id` bigint(20) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `bodega_ubicacion` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO `inventarios` (`id`, `producto_id`, `cantidad`, `bodega_ubicacion`) VALUES
(1, 1, 50, 'Bodega Central - Pasillo C3'),
(2, 2, 15, 'Bodega Central - Pasillo A5');

ALTER TABLE `inventarios`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `inventarios`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;