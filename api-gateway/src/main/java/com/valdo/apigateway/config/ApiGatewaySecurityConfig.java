package com.valdo.apigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;

/**
 * Configuración de seguridad para el API Gateway.
 * <p>
 * Esta clase configura un SecurityWebFilterChain para proteger todos los endpoints
 * que no pertenezcan a /auth/** mediante autenticación básica.
 * </p>
 */
@Configuration
@EnableWebFluxSecurity
public class ApiGatewaySecurityConfig {

    /**
     * Define la cadena de filtros de seguridad para las peticiones que pasan por el gateway.
     *
     * @param http el objeto ServerHttpSecurity a configurar
     * @return la SecurityWebFilterChain resultante
     */
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http
                // Desactiva CSRF (no necesario en aplicaciones REST sin sesión)
                .csrf(csrf -> csrf.disable())
                .authorizeExchange(exchange -> exchange
                        // Permitir el acceso sin autenticación a endpoints de autenticación
                        .pathMatchers("/auth/**").permitAll()
                        // Requiere autenticación para cualquier otra solicitud
                        .anyExchange().authenticated()
                )
                // Se utiliza autenticación básica. Nota: en Postman debes enviar la cabecera Authorization
                .httpBasic(Customizer.withDefaults())
                // Desactiva el login basado en formularios
                .formLogin(form -> form.disable())
                .build();
    }

    /**
     * Configura un almacén de usuarios en memoria para la autenticación.
     * <p>
     * Se definen dos usuarios de prueba: "user" y "admin".
     * </p>
     *
     * @return el MapReactiveUserDetailsService con los usuarios registrados
     */
    @Bean
    public MapReactiveUserDetailsService userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("user123")
                .roles("USER")
                .build();
        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("admin123")
                .roles("USER", "ADMIN")
                .build();
        return new MapReactiveUserDetailsService(user, admin);
    }
}
