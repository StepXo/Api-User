package com.BootcampPragma.Api_User.infrastructure.adapters.securityconfig;

import com.BootcampPragma.Api_User.infrastructure.Utils.InfraConstants;
import com.BootcampPragma.Api_User.infrastructure.adapters.securityconfig.jwtconfiguration.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class ConfigFilter {
    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthenticationFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(InfraConstants.getPath(InfraConstants.ADMIN, InfraConstants.USER)).authenticated()
                        .requestMatchers(InfraConstants.getPath(InfraConstants.ADMIN, InfraConstants.ASTERISKS)).hasRole(InfraConstants.ROLE_ADMIN)
                        .requestMatchers(InfraConstants.getPath(InfraConstants.AUTH, InfraConstants.ASTERISKS)).permitAll()

                        .anyRequest().authenticated())
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public UrlBasedCorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of(System.getenv(InfraConstants.URL)));
        config.setAllowedMethods(List.of(InfraConstants.METHOD));
        config.setAllowedHeaders(List.of(InfraConstants.AUTHORIZATION, InfraConstants.CONTENT));

        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration(InfraConstants.ASTERISKS, config);
        return source;
    }
}
