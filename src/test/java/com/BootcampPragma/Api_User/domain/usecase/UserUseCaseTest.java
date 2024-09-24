package com.BootcampPragma.Api_User.domain.usecase;

import com.BootcampPragma.Api_User.domain.model.RoleEnum;
import com.BootcampPragma.Api_User.domain.model.User;
import com.BootcampPragma.Api_User.domain.spi.UserRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserUseCaseTest {

    @Mock
    private UserRepositoryPort userRepositoryPort;

    @InjectMocks
    private UserUseCase userUseCase;

    private User mockUser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Configuramos el mock de User
        mockUser = User.builder()
                .id(1L)
                .name("John")
                .lastName("Doe")
                .email("john.doe@example.com")
                .password("password123")
                .roleEnum(RoleEnum.USER)
                .idDocument("1234567890")
                .phoneNumber("+123456789012")
                .birthDate("01/01/1980")
                .build();
    }

    @Test
    void testGetUserByIdDocument() {
        when(userRepositoryPort.getUserByIdDocument("1234567890")).thenReturn(mockUser);

        User result = userUseCase.getUserByIdDocument("1234567890");

        assertEquals(mockUser.getId(), result.getId());
        assertEquals(mockUser.getName(), result.getName());
        assertEquals(mockUser.getLastName(), result.getLastName());
        assertEquals(mockUser.getEmail(), result.getEmail());
        assertEquals(mockUser.getIdDocument(), result.getIdDocument());
        assertEquals(mockUser.getPhoneNumber(), result.getPhoneNumber());

        verify(userRepositoryPort, times(1)).getUserByIdDocument("1234567890");
    }
}

