package com.valdo.securityservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Configuración de seguridad para el microservicio de Seguridad.
 * <p>
 * Se configuran usuarios en memoria, se definen los endpoints públicos (/auth/**)
 * y se deshabilitan tanto la autenticación básica como el formulario, ya que se usará JWT para login.
 * </p>
 */
@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    /**
     * Define el almacén de usuarios en memoria.
     *
     * @return InMemoryUserDetailsManager con usuarios iniciales
     */
    @Bean
    public InMemoryUserDetailsManager userDetailsService(PasswordEncoder encoder) {
        UserDetails user = User.builder()
                .username("user")
                .password(encoder.encode("user123"))
                .roles("USER")
                .build();
        UserDetails admin = User.builder()
                .username("admin")
                .password(encoder.encode("admin123"))
                .roles("USER", "ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }


    /**
     * Define el PasswordEncoder.
     * <p>
     * Se utiliza NoOpPasswordEncoder para pruebas. En producción, se recomienda BCryptPasswordEncoder u otro encoder robusto.
     * </p>
     *
     * @return el PasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    /**
     * Configura la cadena de filtros de seguridad para el microservicio de seguridad.
     * <p>
     * Se permiten las peticiones POST a /auth/login y /auth/register sin autenticación.
     * </p>
     *
     * @param http el objeto HttpSecurity a configurar
     * @return SecurityFilterChain configurada
     * @throws Exception en caso de error
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .requestMatchers("/auth/**").permitAll()
                        .anyRequest().authenticated()
                )
                .httpBasic(httpBasic -> httpBasic.disable())
                .formLogin(form -> form.disable());
        return http.build();
    }


    /**
     * Exponemos el AuthenticationManager para su uso en el controlador de autenticación.
     *
     * @param configuration la configuración de autenticación
     * @return el AuthenticationManager
     * @throws Exception en caso de error
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
