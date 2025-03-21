-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 21-03-2025 a las 10:00:00
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `reservas_db`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reservas`
--

CREATE TABLE `reservas` (
  `id` int(20) NOT NULL,
  `cliente` varchar(255) NOT NULL,
  `id_hotel` int(11) NOT NULL,
  `id_vuelo` int(11) NOT NULL,
  `fecha_reserva` timestamp NOT NULL DEFAULT current_timestamp(),
  `total` double NOT NULL,
  `estado` enum('Pendiente','Confirmada','Cancelada') DEFAULT 'Pendiente',
  `dni` varchar(9) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `reservas`
--

INSERT INTO `reservas` (`id`, `cliente`, `id_hotel`, `id_vuelo`, `fecha_reserva`, `total`, `estado`, `dni`) VALUES
(1, 'Juan Pérez', 1, 2, '2025-02-21 13:33:25', 300, 'Confirmada', '12345678A'),
(2, 'María García', 2, 3, '2025-02-21 13:33:25', 450, 'Pendiente', ''),
(3, 'Carlos Ramírez', 3, 1, '2025-02-21 13:33:25', 120, 'Cancelada', ''),
(4, 'Paco', 3, 9, '2025-03-15 10:30:00', 300, 'Pendiente', '22345678B'),
(5, 'Alejandro', 1, 2, '2025-03-15 10:30:00', 300, 'Pendiente', '32345678A'),
(6, 'Alejandro', 1, 1, '2025-03-15 10:30:00', 300, 'Pendiente', '52345678A');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `reservas`
--
ALTER TABLE `reservas`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_hotel` (`id_hotel`),
  ADD KEY `id_vuelo` (`id_vuelo`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `reservas`
--
ALTER TABLE `reservas`
  MODIFY `id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `reservas`
--
ALTER TABLE `reservas`
  ADD CONSTRAINT `reservas_ibfk_1` FOREIGN KEY (`id_hotel`) REFERENCES `hoteles_db`.`hoteles` (`id`),
  ADD CONSTRAINT `reservas_ibfk_2` FOREIGN KEY (`id_vuelo`) REFERENCES `vuelos_db`.`vuelos` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
