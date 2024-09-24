package com.BootcampPragma.Api_User.application.handler;

import com.BootcampPragma.Api_User.application.dto.AuthenticationRequest;
import com.BootcampPragma.Api_User.application.dto.AuthenticationResponse;
import com.BootcampPragma.Api_User.application.dto.UserRequest;
import com.BootcampPragma.Api_User.application.mapper.AuthenticationMapper;
import com.BootcampPragma.Api_User.domain.api.AuthenticationServicePort;
import com.BootcampPragma.Api_User.domain.model.Authentication;
import com.BootcampPragma.Api_User.domain.model.RoleEnum;
import com.BootcampPragma.Api_User.domain.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AuthenticationHandlerTest {

    @Mock
    private AuthenticationServicePort authenticationServicePort;

    @Mock
    private AuthenticationMapper authenticationMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthenticationHandler authenticationHandler;

    private UserRequest userRequest;
    private User savedUser;
    private AuthenticationRequest authenticationRequest;
    private Authentication authentication;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        userRequest = new UserRequest();
        userRequest.setName("John");
        userRequest.setLastName("Doe");
        userRequest.setEmail("john.doe@example.com");
        userRequest.setIdDocument("123456");
        userRequest.setPhoneNumber("+1234567890");
        userRequest.setBirthDate("01/01/1990");
        userRequest.setPassword("password");

        savedUser = User.builder()
                .name("John")
                .lastName("Doe")
                .email("john.doe@example.com")
                .idDocument("123456")
                .phoneNumber("+1234567890")
                .birthDate("01/01/1990")
                .password("encodedPassword")
                .roleEnum(RoleEnum.CLIENT)
                .build();

        authenticationRequest = new AuthenticationRequest();
        authenticationRequest.setEmail("john.doe@example.com");
        authenticationRequest.setPassword("password");

        authentication = Authentication.builder()
                .email("john.doe@example.com")
                .password("password")
                .build();
    }

    @Test
    void testRegister() {

        AuthenticationResponse expectedResponse = new AuthenticationResponse();
        expectedResponse.setToken("token123");

        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(authenticationServicePort.register(any(User.class))).thenReturn(Authentication.builder().build());
        when(authenticationMapper.toAuthenticationResponse(any(Authentication.class))).thenReturn(expectedResponse);

        AuthenticationResponse response = authenticationHandler.register(userRequest, "CLIENT");

        assertNotNull(response);
        assertEquals("token123", response.getToken());

        verify(passwordEncoder).encode(userRequest.getPassword());
        verify(authenticationServicePort).register(any(User.class));
        verify(authenticationMapper).toAuthenticationResponse(any(Authentication.class));
    }

    @Test
    void testLogin() {

        AuthenticationResponse expectedResponse = new AuthenticationResponse();
        expectedResponse.setToken("token123");

        when(authenticationServicePort.login(any(Authentication.class))).thenReturn(authentication);
        when(authenticationMapper.toAuthenticationResponse(any(Authentication.class))).thenReturn(expectedResponse);

        AuthenticationResponse response = authenticationHandler.login(authenticationRequest);

        assertNotNull(response);
        assertEquals("token123", response.getToken());

        verify(authenticationServicePort).login(any(Authentication.class));
        verify(authenticationMapper).toAuthenticationResponse(any(Authentication.class));
    }
}
