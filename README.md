# Sistema de Reservas de una Agencia de Viajes

Este es el proyecto final desarrollado para el módulo de Programación de Servicios y Procesos de 2º DAM. La aplicación se basa en una arquitectura de microservicios que permite gestionar reservas de vuelos y hoteles de forma independiente, escalable y segura.

## Tabla de Contenidos

- [Características](#características)
- [Arquitectura](#arquitectura)
- [Requisitos](#requisitos)
- [Tecnologías](#tecnologías)
- [Ejecución del Proyecto](#ejecución-del-proyecto)
- [Autor](#autor)

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

### Iniciar los Microservicios
**Cada microservicio se ejecuta en un puerto distinto:**

- Eureka Server: *http://localhost:8761*
- API Gateway: *http://localhost:8080*
- Hoteles Service: *http://localhost:8081*
- Vuelos Service: *http://localhost:8082*
- Reservas Service: *http://localhost:8083*
- Security Service: *http://localhost:8084*

## Autor

*Valdo Durán Petronyte*
