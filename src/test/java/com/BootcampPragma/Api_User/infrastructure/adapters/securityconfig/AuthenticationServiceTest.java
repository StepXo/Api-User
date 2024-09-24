package com.BootcampPragma.Api_User.infrastructure.adapters.securityconfig;

import com.BootcampPragma.Api_User.domain.model.Authentication;
import com.BootcampPragma.Api_User.infrastructure.adapters.persistance.entity.UserEntity;
import com.BootcampPragma.Api_User.infrastructure.adapters.persistance.repository.UserRepository;
import com.BootcampPragma.Api_User.infrastructure.adapters.securityconfig.jwtconfiguration.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AuthenticationServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private JwtService jwtService;

    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private AuthenticationService authenticationService;
    private Authentication request;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        request = Authentication.builder()
                .email("john.doe@example.com")
                .password("wrongpassword")
                .build();
    }

    @Test
    void testAuthenticateSuccess() {

        UserEntity user =new UserEntity();
        user.setEmail("john.doe@example.com");

        when(userRepository.findByEmail(request.getEmail())).thenReturn(Optional.of(user));
        when(jwtService.getToken(user)).thenReturn("generatedToken");

        String token = authenticationService.authenticate(request);

        assertNotNull(token);
        assertEquals("generatedToken", token);

        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(userRepository).findByEmail(request.getEmail());
        verify(jwtService).getToken(user);
    }

    @Test
    void testAuthenticateUserNotFound() {

        when(userRepository.findByEmail(request.getEmail())).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> authenticationService.authenticate(request));

        verify(userRepository).findByEmail(request.getEmail());
        verify(jwtService, never()).getToken(any());
    }

    @Test
    void testAuthenticateWithInvalidCredentials() {

        doThrow(AuthenticationException.class).when(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));

        assertThrows(AuthenticationException.class, () -> authenticationService.authenticate(request));

        verify(userRepository, never()).findByEmail(anyString());
        verify(jwtService, never()).getToken(any());
    }
}
