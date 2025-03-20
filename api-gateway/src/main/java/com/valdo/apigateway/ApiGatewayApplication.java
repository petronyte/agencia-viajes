package com.valdo.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal del API Gateway.
 * <p>
 * Este microservicio actúa como único punto de entrada para todas las peticiones,
 * redirigiéndolas a los microservicios correspondientes mediante Spring Cloud Gateway.
 * </p>
 */
@SpringBootApplication
public class ApiGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }
}
