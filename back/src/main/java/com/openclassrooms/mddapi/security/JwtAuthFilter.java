package com.openclassrooms.mddapi.security;

import com.openclassrooms.mddapi.security.service.UserDetailsServiceImpl;
import com.openclassrooms.mddapi.security.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final UserDetailsServiceImpl userDetailsServiceImpl;
    private final JwtUtils jwtUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {

        // Permet de ne pas rentrer dans la vérification de ce filtre si ce sont ces routes, sinon ça provoque une erreur
        if(request.getServletPath().contains("/auth/register") || request.getServletPath().contains("/auth/login") || request.getServletPath().contains("/uploads/")) {
            filterChain.doFilter(request, response);
            return;
        }

        // Récupération de l'en-tête Authorization
        String authHeader = request.getHeader("Authorization");
        String token = null;
        String email = null;

        // Vérifie si le header commence par "Bearer "
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7); // Extrait le token
            email = jwtUtils.extractEmail(token); // Extrait l'email du token (modifier dans JwtService si nécessaire)
        }

        // Si l'email est valide et l'utilisateur n'est pas déjà authentifié
        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(email);

            // Valide le token et établit l'authentification
            if (jwtUtils.validateToken(token, userDetails)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        // Continue la chaîne de filtres
        filterChain.doFilter(request, response);
    }
}