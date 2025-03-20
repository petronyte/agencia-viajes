package com.valdo.reservasservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.client.RestTemplate;

/**
 * Configuración para inyectar un RestTemplate LoadBalanced.
 *
 * Este bean permite realizar llamadas a otros microservicios utilizando la resolución de nombres a través de Euskera.
 */
@Configuration
public class RestTemplateConfig {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
