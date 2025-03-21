-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 21-03-2025 a las 10:00:59
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
-- Base de datos: `vuelos_db`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `hoteles`
--

CREATE TABLE `hoteles` (
  `id` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `ubicacion` varchar(255) NOT NULL,
  `estrellas` int(11) DEFAULT NULL CHECK (`estrellas` between 1 and 5),
  `precio_por_noche` decimal(10,2) NOT NULL,
  `disponibilidad` tinyint(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `hoteles`
--

INSERT INTO `hoteles` (`id`, `nombre`, `ubicacion`, `estrellas`, `precio_por_noche`, `disponibilidad`) VALUES
(1, 'Hotel Andaluz', 'Sevilla, España', 4, 120.00, 1),
(2, 'Resort Caribe', 'Punta Cana, República Dominicana', 5, 250.00, 1),
(3, 'Hostal Madrid', 'Madrid, España', 2, 50.00, 1),
(4, 'Hotel Andaluz', 'Sevilla, España', 4, 120.00, 1),
(5, 'Resort Caribe', 'Punta Cana, República Dominicana', 5, 250.00, 1),
(6, 'Hostal Madrid', 'Madrid, España', 2, 50.00, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `vuelos`
--

CREATE TABLE `vuelos` (
  `id` int(11) NOT NULL,
  `aerolinea` varchar(255) NOT NULL,
  `origen` varchar(255) NOT NULL,
  `destino` varchar(255) NOT NULL,
  `fecha_salida` date NOT NULL,
  `hora_salida` time NOT NULL,
  `precio` double NOT NULL,
  `asientos_disponibles` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `vuelos`
--

INSERT INTO `vuelos` (`id`, `aerolinea`, `origen`, `destino`, `fecha_salida`, `hora_salida`, `precio`, `asientos_disponibles`) VALUES
(1, 'Iberia', 'Madrid', 'Barcelona', '2025-03-10', '08:00:00', 75, 45),
(2, 'Air Europa', 'Sevilla', 'París', '2025-03-15', '10:30:00', 150, 99),
(3, 'Vueling', 'Barcelona', 'Londres', '2025-03-20', '12:00:00', 180, 100),
(4, 'Iberia', 'Madrid', 'Barcelona', '2025-03-10', '08:00:00', 75, 150),
(5, 'Air Europa', 'Sevilla', 'París', '2025-03-15', '10:30:00', 150, 120),
(6, 'Vueling', 'Barcelona', 'Londres', '2025-03-20', '12:00:00', 180, 80),
(7, 'Iberia', 'Madrid', 'Barcelona', '2025-03-10', '08:00:00', 75, 150),
(8, 'Air Europa', 'Sevilla', 'París', '2025-03-15', '10:30:00', 150, 120),
(9, 'Vueling', 'Barcelona', 'Londres', '2025-03-20', '12:00:00', 180, 79);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `hoteles`
--
ALTER TABLE `hoteles`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `vuelos`
--
ALTER TABLE `vuelos`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `hoteles`
--
ALTER TABLE `hoteles`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `vuelos`
--
ALTER TABLE `vuelos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
