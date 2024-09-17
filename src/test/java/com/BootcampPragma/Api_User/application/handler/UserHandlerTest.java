package com.BootcampPragma.Api_User.application.handler;

import com.BootcampPragma.Api_User.application.dto.UserRequestDto;
import com.BootcampPragma.Api_User.application.dto.UserResponseDto;
import com.BootcampPragma.Api_User.application.mapper.UserResponseMapper;
import com.BootcampPragma.Api_User.domain.api.UserServicePort;
import com.BootcampPragma.Api_User.domain.model.RoleEnum;
import com.BootcampPragma.Api_User.domain.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UserHandlerTest {

    @Mock
    private UserServicePort userServicePort;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UserResponseMapper userResponseMapper;

    @InjectMocks
    private UserHandler userHandler;
    private UserRequestDto userDto;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        // Arrange
        userDto = new UserRequestDto();

        userDto.setName("John");
        userDto.setLastName("Doe");
        userDto.setEmail("john.doe@example.com");
        userDto.setIdDocument("123456");
        userDto.setPhoneNumber("+123456789012");
        userDto.setBirthDate("01/01/2000");
        userDto.setPassword("password");
    }

    @Test
    void testRegister() {

        User expectedUser = User.builder()
                .name("John")
                .lastName("Doe")
                .email("john.doe@example.com")
                .idDocument("123456")
                .phoneNumber("+123456789012")
                .birthDate("01/01/2000")
                .password("encodedPassword")
                .roleEnum(RoleEnum.USER)
                .build();

        when(passwordEncoder.encode("password")).thenReturn("encodedPassword");

        // Act
        UserRequestDto result = userHandler.register(userDto);

        // Assert
        verify(userServicePort).register(argThat(new ArgumentMatcher<User>() {
            @Override
            public boolean matches(User actualUser) {
                return actualUser.getName().equals(expectedUser.getName())
                        && actualUser.getLastName().equals(expectedUser.getLastName())
                        && actualUser.getEmail().equals(expectedUser.getEmail())
                        && actualUser.getIdDocument().equals(expectedUser.getIdDocument())
                        && actualUser.getPhoneNumber().equals(expectedUser.getPhoneNumber())
                        && actualUser.getBirthDate().equals(expectedUser.getBirthDate())
                        && actualUser.getPassword().equals(expectedUser.getPassword())
                        && actualUser.getRole().equals(expectedUser.getRole());
            }
        }));
        assertEquals(userDto, result);
    }

    @Test
    void testGetUserByIdDocument() {
        // Arrange
        String id = "123456";
        User user = User.builder()
                .name("John")
                .lastName("Doe")
                .email("john.doe@example.com")
                .idDocument("123456")
                .phoneNumber("+123456789012")
                .birthDate("01/01/2000")
                .password("encodedPassword")
                .roleEnum(RoleEnum.USER)
                .build();
        UserResponseDto userResponseDto = new UserResponseDto();

        when(userServicePort.getUserByIdDocument(id)).thenReturn(user);
        when(userResponseMapper.toUserResponseDto(user)).thenReturn(userResponseDto);

        // Act
        UserResponseDto result = userHandler.getUserByIdDocument(id);

        // Assert
        verify(userServicePort).getUserByIdDocument(id);
        verify(userResponseMapper).toUserResponseDto(user);
        assertEquals(userResponseDto, result);
    }

}
