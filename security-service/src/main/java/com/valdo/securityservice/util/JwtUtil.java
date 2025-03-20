package com.valdo.securityservice.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Utilidad para generar y validar tokens JWT.
 * <p>
 * Esta clase permite extraer información del token, generar un token
 * y validar si éste ha expirado.
 * </p>
 */
@Component
public class JwtUtil {

    // Clave secreta para firmar los tokens (en producción, almacenar de forma segura)
    private final String SECRET_KEY = "my_super_secret_key_that_is_at_least_32_bytes_long!";

    // Tiempo de expiración del token en milisegundos (por ejemplo, 10 horas)
    private final long EXPIRATION_TIME = 10 * 60 * 60 * 1000;

    /**
     * Extrae el nombre de usuario (subject) del token.
     *
     * @param token el token JWT
     * @return el nombre de usuario contenido en el token
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Extrae la fecha de expiración del token.
     *
     * @param token el token JWT
     * @return la fecha de expiración
     */
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * Extrae un reclamo específico del token usando una función.
     *
     * @param token el token JWT
     * @param claimsResolver función para resolver el reclamo deseado
     * @param <T> el tipo del reclamo
     * @return el reclamo extraído
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Extrae todos los reclamos del token.
     *
     * @param token el token JWT
     * @return los reclamos contenidos en el token
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Verifica si el token ha expirado.
     *
     * @param token el token JWT
     * @return true si el token ha expirado, false en caso contrario
     */
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * Genera un token JWT para un usuario.
     *
     * @param username el nombre del usuario
     * @return el token JWT generado
     */
    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, username);
    }

    /**
     * Crea el token JWT con los reclamos, subject, fecha de emisión y expiración.
     *
     * @param claims  los reclamos a incluir en el token
     * @param subject el subject (usualmente el nombre de usuario)
     * @return el token JWT
     */
    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8)), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Valida que el token sea correcto y no haya expirado para un usuario dado.
     *
     * @param token    el token JWT
     * @param username el nombre de usuario a validar
     * @return true si el token es válido y corresponde al usuario, false en caso contrario
     */
    public Boolean validateToken(String token, String username) {
        return extractUsername(token).equals(username) && !isTokenExpired(token);
    }
}
