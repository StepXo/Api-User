package com.BootcampPragma.Api_User.domain.usecase;

import com.BootcampPragma.Api_User.domain.exeption.UserEmailAlreadyExistsException;
import com.BootcampPragma.Api_User.domain.exeption.UserIdAlreadyExistsException;
import com.BootcampPragma.Api_User.domain.model.User;
import com.BootcampPragma.Api_User.domain.spi.UserRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserUseCaseTest {

    @Mock
    private UserRepositoryPort userRepositoryPort;

    @InjectMocks
    private UserUseCase userUseCase;
    private User user;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        user = User.builder()
                .name("John")
                .lastName("Doe")
                .email("john.doe@example.com")
                .idDocument("123456")
                .phoneNumber("+123456789012")
                .birthDate("01/01/2000")
                .password("password")
                .build();

    }

    @Test
    void testRegisterUser_EmailAlreadyExists() {

        when(userRepositoryPort.getUserByEmail(user.getEmail())).thenReturn(user);

        UserEmailAlreadyExistsException thrown = assertThrows(
                UserEmailAlreadyExistsException.class,
                () -> userUseCase.register(user)
        );

        assertNotNull(thrown);
        verify(userRepositoryPort, never()).register(any(User.class));
    }

    @Test
    void testRegisterUser_IdAlreadyExists() {

        when(userRepositoryPort.getUserByEmail(user.getEmail())).thenReturn(null);
        when(userRepositoryPort.getUserByIdDocument(user.getIdDocument())).thenReturn(user);

        // Act & Assert
        UserIdAlreadyExistsException thrown = assertThrows(
                UserIdAlreadyExistsException.class,
                () -> userUseCase.register(user)
        );

        assertNotNull(thrown);
        verify(userRepositoryPort, never()).register(any(User.class));
    }

    @Test
    void testRegisterUser_Success() {

        when(userRepositoryPort.getUserByEmail(user.getEmail())).thenReturn(null);
        when(userRepositoryPort.getUserByIdDocument(user.getIdDocument())).thenReturn(null);
        when(userRepositoryPort.register(ArgumentMatchers.any(User.class))).thenReturn(user);

        User result = userUseCase.register(user);

        assertNotNull(result);
        assertEquals(user, result);
        verify(userRepositoryPort).register(user);
    }

    @Test
    void testGetUserByIdDocument() {
        String idDocument = "123456";

        when(userRepositoryPort.getUserByIdDocument(idDocument)).thenReturn(user);

        User result = userUseCase.getUserByIdDocument(idDocument);

        assertNotNull(result);
        assertEquals(user, result);
        verify(userRepositoryPort).getUserByIdDocument(idDocument);
    }
}
