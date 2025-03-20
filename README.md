# Sistema de Reservas de una Agencia de Viajes

Este es el proyecto final desarrollado para el curso de PSP del 2º DAM del IES Antonio Gala. La aplicación se basa en una arquitectura de microservicios que permite gestionar reservas de vuelos y hoteles de forma independiente, escalable y segura.

## Tabla de Contenidos

- [Características](#características)
- [Arquitectura](#arquitectura)
- [Requisitos](#requisitos)
- [Tecnologías](#tecnologías)
- [Ejecución del Proyecto](#ejecución-del-proyecto)
- [Endpoints Principales](#endpoints-principales)
- [Pruebas y Uso](#pruebas-y-uso)
- [Despliegue](#despliegue)
- [Documentación y Presentación](#documentación-y-presentación)
- [Autores](#autores)
- [Licencia](#licencia)

## Características

- **Microservicios Independientes:**  
  - **Hoteles Service:** Consulta la lista de hoteles disponibles.
  - **Vuelos Service:** Consulta la lista de vuelos y actualiza la disponibilidad de plazas.
  - **Reservas Service:** Registra nuevas reservas y consulta las existentes.
  - **Security Service:** Gestiona la autenticación y el registro de usuarios mediante Spring Security (con opción a JWT).
  - **API Gateway:** Centraliza el acceso a los microservicios y gestiona la seguridad de las peticiones.
  - **Eureka Server:** Permite el descubrimiento dinámico de microservicios.

- **Bases de Datos Independientes:** Cada servicio utiliza su propia base de datos para un mayor desacoplamiento.

- **Seguridad:**  
  - Autenticación y autorización con Spring Security.
  - Emisión y validación de tokens JWT (opcional).

- **Código Modular y Bien Documentado:** Diseño orientado a mantener una arquitectura escalable y de fácil mantenimiento.

## Arquitectura

La solución se compone de los siguientes microservicios:

1. **Eureka Server:** Registro y descubrimiento de microservicios.
2. **API Gateway:** Punto de entrada para las solicitudes, gestionando la seguridad y el balanceo de carga.
3. **Hoteles Service:** Servicio REST para obtener hoteles disponibles.
4. **Vuelos Service:** Servicio REST para obtener vuelos y actualizar plazas.
5. **Reservas Service:** Servicio REST para gestionar reservas.
6. **Security Service:** Servicio REST para la autenticación y registro de usuarios.

Los microservicios se comunican mediante peticiones HTTP y se integran mediante Eureka y el API Gateway.

## Requisitos

- Microservicios independientes.
- Bases de datos independientes para cada servicio.
- Descubrimiento de servicios con Eureka Server.
- Autenticación con Spring Security (con opción a JWT).
- API Gateway para la gestión de peticiones y seguridad.
- (Opcional) Despliegue con Docker y Kubernetes.
- (Opcional) Cliente frontend.

## Tecnologías

- **Java 17**
- **Spring Boot 3.4.3**
- **Spring Security**
- **Spring Cloud (Eureka y Gateway)**
- **Spring Data JPA**
- **MySQL**
- **JWT (opcional)**
- **Maven**
- **Docker y Kubernetes (opcional)**

## Ejecución del Proyecto

### Prerrequisitos

- **JDK 17** o superior.
- **MySQL** instalado y configurado.
- **Maven**.
- (Opcional) **Docker** y **Kubernetes** para despliegue en producción.

### Configuración de la Base de Datos

Ejecuta el siguiente script en tu MySQL para crear la base de datos y las tablas necesarias (ya se han creado manualmente según nuestro dump):

```sql
-- Crear la base de datos
CREATE DATABASE security_db;

USE security_db;

-- Tabla roles
CREATE TABLE roles (
  rol VARCHAR(255) NOT NULL,
  user VARCHAR(50) NOT NULL,
  username VARCHAR(255) NOT NULL,
  role VARCHAR(255) NOT NULL,
  PRIMARY KEY (rol),
  KEY fk_role_user (user)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Tabla users
CREATE TABLE users (
  user VARCHAR(50) NOT NULL,
  pwd VARCHAR(255) NOT NULL,
  enabled BIT(1) NOT NULL,
  username VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  PRIMARY KEY (user),
  UNIQUE KEY UK_username (username)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Establecer la relación entre roles y users
ALTER TABLE roles
  ADD CONSTRAINT fk_role_user FOREIGN KEY (user) REFERENCES users (user);
