package com.BootcampPragma.Api_User.infrastructure.adapters.securityconfig.jwtconfiguration;

import com.BootcampPragma.Api_User.domain.model.RoleEnum;
import com.BootcampPragma.Api_User.infrastructure.adapters.persistance.entity.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.SignatureException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class JwtServiceTest {

    private static final String SECRET_KEY = "mi2345mama5rhnse567Me3yagzrt5ygmimafyghseyae5yrthmiMamasatresysthmeAma";

    @InjectMocks
    private JwtService jwtService;

    @Mock
    private UserDetails userDetails;

    @Mock
    private UserEntity userEntity;
    private Map<String, Object> claims;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        claims = new HashMap<>();
        claims.put("extraClaim", "extraValue");
    }

    @Test
    void testGenerateToken() {
        when(userEntity.getEmail()).thenReturn("user@example.com");
        when(userEntity.getRole()).thenReturn(RoleEnum.valueOf("USER"));

        String token = jwtService.generate(userEntity);

        assertNotNull(token);
        assertTrue(token.startsWith("eyJ")); // El token debe comenzar con el encabezado codificado en base64
    }

    @Test
    void testExtractUsername() {
        when(userEntity.getEmail()).thenReturn("user@example.com");
        String token = jwtService.generate(userEntity);

        String extractedUsername = jwtService.extractUsername(token);

        assertEquals("user@example.com", extractedUsername);
    }

    @Test
    void testIsTokenValid() {
        when(userEntity.getEmail()).thenReturn("user@example.com");
        when(userDetails.getUsername()).thenReturn("user@example.com");

        String token = jwtService.generate(userEntity);

        boolean isValid = jwtService.isTokenValid(token, userDetails);

        assertTrue(isValid);
    }

    @Test
    void testIsTokenInvalid() {
        String invalidToken = "invalid.token";
        when(userDetails.getUsername()).thenReturn("user@example.com");

        boolean isValid = jwtService.isTokenValid(invalidToken, userDetails);

        assertFalse(isValid);
    }


    @Test
    void testGenerateTokenWithExtraClaims() {
        when(userEntity.getEmail()).thenReturn("user@example.com");

        String token = jwtService.generateToken(claims, userEntity);

        assertNotNull(token);
        assertTrue(token.startsWith("eyJ")); // El token debe comenzar con el encabezado codificado en base64
    }
}
