package com.openclassrooms.mddapi.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Permet de gérer les opérations liées aux JWT.
 * Cette classe permet de générer, valider et extraire des informations des tokens JWT.
 * Elle repose sur la bibliothèque JJWT.
 */
@Component
public class JwtUtils {

    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    /**
     * Clé secrète pour signer les JWT.
     * Chargée depuis les propriétés d'application.
     */
    @Value("${JWT_KEY}")
    private String secretKey;

    /**
     * Durée d'expiration des JWT en millisecondes.
     * Chargée depuis les propriétés d'application.
     */
    @Value("${JWT_EXPIRATION}")
    private long jwtExpiration;

    /**
     * Génère un token JWT pour un utilisateur à partir de son email.
     *
     * @param email L'email de l'utilisateur.
     * @return Un token JWT.
     */
    public String generateToken(String email) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, email);
    }

    /**
     * Crée un token JWT avec des claims spécifiques.
     *
     * @param claims Les données supplémentaires à inclure dans le token.
     * @param email  L'email de l'utilisateur, utilisé comme sujet du token.
     * @return Un token JWT.
     */
    private String createToken(Map<String, Object> claims, String email) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Récupère la clé de signature pour signer ou valider les tokens.
     *
     * @return Une clé cryptographique dérivée de la clé secrète.
     */
    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * Extrait l'email d'un token JWT.
     *
     * @param token Le token JWT.
     * @return L'email contenu dans le token.
     */
    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Extrait un claim spécifique d'un token JWT.
     *
     * @param <T> Le type de la donnée à extraire.
     * @param token Le token JWT.
     * @param claimsResolver La fonction pour extraire le claim.
     * @return La valeur du claim extrait.
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Extrait tous les claims (données) d'un token JWT.
     *
     * @param token Le token JWT.
     * @return Un objet {@link Claims} contenant toutes les données du token.
     * @throws IllegalArgumentException Si le token est invalide.
     */
    private Claims extractAllClaims(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(getSignKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (JwtException e) {
            logger.error("Impossible d'extraire les claims : {}", e.getMessage());
            throw new IllegalArgumentException("Token JWT invalide");
        }
    }

    /**
     * Valide un token JWT en le comparant avec les informations d'un utilisateur.
     *
     * @param token Le token JWT.
     * @param userDetails Les détails de l'utilisateur pour comparaison.
     * @return {@code true} si le token est valide, sinon {@code false}.
     */
    public boolean validateToken(String token, UserDetails userDetails) {
        try {
            final String email = extractEmail(token);
            return (email.equals(userDetails.getUsername()) && !isTokenExpired(token));
        } catch (JwtException e) {
            logger.error("Échec de la validation du token : {}", e.getMessage());
            return false;
        }
    }

    /**
     * Vérifie si un token JWT a expiré.
     *
     * @param token Le token JWT.
     * @return {@code true} si le token a expiré, sinon {@code false}.
     */
    private boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }

}