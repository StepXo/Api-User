package com.BootcampPragma.Api_User.application.handler;

import com.BootcampPragma.Api_User.application.dto.UserRequest;
import com.BootcampPragma.Api_User.application.dto.UserResponse;
import com.BootcampPragma.Api_User.application.mapper.UserHandlerMapper;
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
    private UserHandlerMapper userHandlerMapper;

    @InjectMocks
    private UserHandler userHandler;
    private UserRequest userDto;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        // Arrange
        userDto = new UserRequest();

        userDto.setName("John");
        userDto.setLastName("Doe");
        userDto.setEmail("john.doe@example.com");
        userDto.setIdDocument("123456");
        userDto.setPhoneNumber("+123456789012");
        userDto.setBirthDate("01/01/2000");
        userDto.setPassword("password");
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
        UserResponse userResponse = new UserResponse();

        when(userServicePort.getUserByIdDocument(id)).thenReturn(user);
        when(userHandlerMapper.toUserResponseDto(user)).thenReturn(userResponse);

        // Act
        UserResponse result = userHandler.getUserByIdDocument(id);

        // Assert
        verify(userServicePort).getUserByIdDocument(id);
        verify(userHandlerMapper).toUserResponseDto(user);
        assertEquals(userResponse, result);
    }

}
