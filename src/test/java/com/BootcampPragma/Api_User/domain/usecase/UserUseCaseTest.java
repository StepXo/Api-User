package com.BootcampPragma.Api_User.domain.usecase;

import com.BootcampPragma.Api_User.domain.model.User;
import com.BootcampPragma.Api_User.domain.spi.UserRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
    void testGetUserByIdDocument() {

        when(userRepositoryPort.getUserById(1)).thenReturn(user);

        User result = userUseCase.getUserById("1234567890");


        assertNotNull(result);
        assertEquals(user, result);

        verify(userRepositoryPort, times(1)).getUserById(1);
    }
}
