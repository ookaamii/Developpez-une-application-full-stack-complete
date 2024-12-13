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

@Component
public class JwtUtils {

    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    @Value("${JWT_KEY}")
    private String secretKey;

    @Value("${JWT_EXPIRATION}")
    private long jwtExpiration;

    // Génère un JWT avec des claims additionnels et un email
    public String generateToken(String email) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, email);
    }

    // Crée un JWT avec des claims spécifiques
    private String createToken(Map<String, Object> claims, String email) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // Récupère la clé de signature encodée
    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // Extrait l'email (ou tout autre claim) du token
    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Extrait un claim spécifique du token
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // Extrait tous les claims du token
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

    // Valide un token avec un UserDetails
    public boolean validateToken(String token, UserDetails userDetails) {
        try {
            final String email = extractEmail(token);
            return (email.equals(userDetails.getUsername()) && !isTokenExpired(token));
        } catch (JwtException e) {
            logger.error("Échec de la validation du token : {}", e.getMessage());
            return false;
        }
    }

    // Vérifie si un token a expiré
    private boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }

    // Vérifie si l'utilisateur est authentifié dans le contexte de sécurité
    public void checkAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    }

    // Valide un token brut et journalise les erreurs spécifiques
    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSignKey())
                    .build()
                    .parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            logger.error("Signature JWT invalide : {}", e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("Token JWT mal formé : {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("Token JWT expiré : {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("Token JWT non supporté : {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("La chaîne de claims JWT est vide : {}", e.getMessage());
        }
        return false;
    }
}