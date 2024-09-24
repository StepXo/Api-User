package com.BootcampPragma.Api_User.domain.usecase;

import com.BootcampPragma.Api_User.domain.exeption.*;
import com.BootcampPragma.Api_User.domain.model.Authentication;
import com.BootcampPragma.Api_User.domain.model.User;
import com.BootcampPragma.Api_User.domain.spi.AuthenticationRepositoryPort;
import com.BootcampPragma.Api_User.domain.spi.UserRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AuthenticationUseCaseTest {

    @Mock
    private UserRepositoryPort userRepositoryPort;

    @Mock
    private AuthenticationRepositoryPort authenticationRepositoryPort;

    @InjectMocks
    private AuthenticationUseCase authenticationUseCase;
    private User user;
    private Authentication authentication;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user = User.builder()
                .email("john.doe@example.com")
                .password("password123")
                .idDocument("123456")
                .build();
        authentication = Authentication.builder()
                .email("john.doe@example.com")
                .password("password123")
                .build();
    }

    @Test
    void testRegisterSuccess() {


        when(userRepositoryPort.getUserByEmail(user.getEmail())).thenReturn(null);
        when(userRepositoryPort.getUserByIdDocument(user.getIdDocument())).thenReturn(null);
        when(userRepositoryPort.register(any(User.class))).thenReturn("generatedToken");

        Authentication response = authenticationUseCase.register(user);

        assertNotNull(response);
        assertEquals("john.doe@example.com", response.getEmail());
        assertEquals("generatedToken", response.getToken());

        verify(userRepositoryPort).getUserByEmail(user.getEmail());
        verify(userRepositoryPort).getUserByIdDocument(user.getIdDocument());
        verify(userRepositoryPort).register(user);
    }

    @Test
    void testRegisterEmailAlreadyExists() {


        when(userRepositoryPort.getUserByEmail(user.getEmail())).thenReturn(user);

        assertThrows(UserEmailAlreadyExistsException.class, () -> authenticationUseCase.register(user));

        verify(userRepositoryPort).getUserByEmail(user.getEmail());
        verify(userRepositoryPort, never()).getUserByIdDocument(any());
        verify(userRepositoryPort, never()).register(any());
    }

    @Test
    void testRegisterIdDocumentAlreadyExists() {

        when(userRepositoryPort.getUserByEmail(user.getEmail())).thenReturn(user);
        when(userRepositoryPort.getUserByIdDocument(user.getIdDocument())).thenReturn(user);

        assertThrows(UserIdAlreadyExistsException.class, () -> authenticationUseCase.register(user));

        verify(userRepositoryPort).getUserByEmail(user.getEmail());
        verify(userRepositoryPort).getUserByIdDocument(user.getIdDocument());
        verify(userRepositoryPort, never()).register(any());
    }

    @Test
    void testLoginSuccess() {

        when(userRepositoryPort.getUserByEmail(authentication.getEmail())).thenReturn(user);
        when(authenticationRepositoryPort.authenticate(any(Authentication.class))).thenReturn("generatedToken");

        Authentication response = authenticationUseCase.login(authentication);

        assertNotNull(response);
        assertEquals("generatedToken", response.getToken());

        verify(userRepositoryPort).getUserByEmail(authentication.getEmail());
        verify(authenticationRepositoryPort).authenticate(authentication);
    }

    @Test
    void testLoginUserNotFound() {

        when(userRepositoryPort.getUserByEmail(authentication.getEmail())).thenReturn(null);

        assertThrows(UserNotFound.class, () -> authenticationUseCase.login(authentication));

        verify(userRepositoryPort).getUserByEmail(authentication.getEmail());
        verify(authenticationRepositoryPort, never()).authenticate(any());
    }

    @Test
    void testLoginPasswordNull() {

        authentication.setPassword(null);

        assertThrows(PasswordFormatException.class, () -> authenticationUseCase.login(authentication));

        verify(userRepositoryPort, never()).getUserByEmail(any());
        verify(authenticationRepositoryPort, never()).authenticate(any());
    }

}
