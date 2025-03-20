package com.valdo.securityservice.controller;

import com.valdo.securityservice.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador para la autenticación y registro de usuarios.
 * <p>
 * Proporciona endpoints para login (/auth/login) y registro (/auth/register).
 * </p>
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private InMemoryUserDetailsManager inMemoryUserDetailsManager;

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * Endpoint para iniciar sesión.
     * <p>
     * Se autentican las credenciales y, si son válidas, se genera un token JWT.
     * </p>
     *
     * @param authRequest objeto que contiene el username y password
     * @return AuthResponse con el token JWT
     */
    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthRequest authRequest) {
        // Autentica las credenciales
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );
        // Carga los detalles del usuario autenticado
        final UserDetails userDetails = inMemoryUserDetailsManager.loadUserByUsername(authRequest.getUsername());
        // Genera el token JWT
        final String token = jwtUtil.generateToken(userDetails.getUsername());
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @Autowired
    private PasswordEncoder passwordEncoder;


    /**
     * Endpoint para registrar un nuevo usuario.
     *
     * @param authRequest objeto que contiene el username y password
     * @return mensaje de confirmación o error si el usuario ya existe
     */
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody AuthRequest authRequest) {
        if (inMemoryUserDetailsManager.userExists(authRequest.getUsername())) {
            return ResponseEntity.badRequest().body("User already exists");
        }
        // Inyecta el PasswordEncoder (puedes agregarlo como @Autowired en el controlador)
        UserDetails newUser = User.builder()
                .username(authRequest.getUsername())
                .password(passwordEncoder.encode(authRequest.getPassword()))
                .roles("USER")
                .build();
        inMemoryUserDetailsManager.createUser(newUser);
        return ResponseEntity.ok("User registered successfully");
    }

}
