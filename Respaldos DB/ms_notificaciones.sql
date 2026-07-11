SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

CREATE TABLE `notificaciones` (
  `id` bigint(20) NOT NULL,
  `usuario_id` bigint(20) NOT NULL,
  `mensaje` text NOT NULL,
  `tipo` varchar(30) NOT NULL,
  `leida` tinyint(1) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO `notificaciones` (`id`, `usuario_id`, `mensaje`, `tipo`, `leida`) VALUES
(1, 1, 'Tu pago ha sido aprobado con éxito. La orden #80415 está en preparación.', 'EMAIL', 0);

ALTER TABLE `notificaciones`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `notificaciones`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;