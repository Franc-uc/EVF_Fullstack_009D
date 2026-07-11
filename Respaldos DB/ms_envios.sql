SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

CREATE TABLE `envios` (
  `id` bigint(20) NOT NULL,
  `pedido_id` bigint(20) NOT NULL,
  `direccion_envio` varchar(255) NOT NULL,
  `ciudad` varchar(100) NOT NULL,
  `codigo_postal` varchar(20) NOT NULL,
  `estado` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO `envios` (`id`, `pedido_id`, `direccion_envio`, `ciudad`, `codigo_postal`, `estado`) VALUES
(1, 1, 'Av. Concha y Toro 1340', 'Puente Alto', '8150000', 'PENDIENTE');

ALTER TABLE `envios`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `envios`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;