/**
 * Configuration pour la sécurité de l'appli.
 * Cette classe configure la sécurité de l'application en utilisant Spring Security.
 * Elle gère :
 * - La gestion des CORS.
 * - La configuration des filtres de sécurité (ex. : JWT).
 * - La stratégie de gestion des sessions (stateless).
 * - L'encodage des mots de passe.
 */
package com.openclassrooms.mddapi.security;

import com.openclassrooms.mddapi.security.service.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * Configuration principale de la sécurité.
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SpringSecurityConfig {

    private final JwtUtils jwtUtils;
    private final UserDetailsServiceImpl userDetailsServiceImpl;

    /**
     * Configure la chaîne de filtres de sécurité.
     * Les fonctionnalités incluent :
     * <ul>
     *     <li>Désactivation de CSRF.</li>
     *     <li>Configuration des CORS.</li>
     *     <li>Politique de session stateless.</li>
     *     <li>Ajout d'un filtre JWT pour l'authentification des requêtes.</li>
     *     <li>Accès autorisé uniquement pour certaines routes publiques.</li>
     * </ul>
     *
     * @param http L'objet {@link HttpSecurity} pour configurer la sécurité.
     * @return La chaîne de filtres configurée.
     * @throws Exception En cas de problème lors de la configuration.
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .cors(withDefaults()) // CORS intégré
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/login", "/auth/register", "/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
                        .anyRequest().authenticated())
                .addFilterBefore(new JwtAuthFilter(userDetailsServiceImpl, jwtUtils), UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    /**
     * Configure les règles de CORS.
     * Permet les requêtes provenant de certaines origines, avec des méthodes et en-têtes spécifiques.
     * @return La source de configuration CORS.
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:4200"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    /**
     * Configure l'AuthenticationManager pour Spring Security.
     * Utilise un service {@link UserDetailsServiceImpl} pour charger les utilisateurs
     * et un {@link PasswordEncoder} pour encoder les mots de passe.
     * @param http L'objet {@link HttpSecurity}.
     * @param passwordEncoder L'encodeur de mot de passe.
     * @return Le gestionnaire d'authentification configuré.
     * @throws Exception En cas de problème lors de la configuration.
     */
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, PasswordEncoder passwordEncoder) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsServiceImpl).passwordEncoder(passwordEncoder);
        return authenticationManagerBuilder.build();
    }

    /**
     * Définit un encodeur de mot de passe basé sur l'algorithme BCrypt.
     *
     * @return L'instance de {@link PasswordEncoder}.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
