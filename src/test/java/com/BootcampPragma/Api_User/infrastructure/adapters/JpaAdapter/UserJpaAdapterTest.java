package com.BootcampPragma.Api_User.infrastructure.adapters.JpaAdapter;

import com.BootcampPragma.Api_User.domain.model.User;
import com.BootcampPragma.Api_User.infrastructure.adapters.persistance.entity.UserEntity;
import com.BootcampPragma.Api_User.infrastructure.adapters.persistance.mapper.UserMapper;
import com.BootcampPragma.Api_User.infrastructure.adapters.persistance.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

class UserJpaAdapterTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserJpaAdapter userJpaAdapter;

    private User user;
    private UserEntity userEntity;

    @BeforeEach
    void setUp() {
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

        userEntity = new UserEntity();
        userEntity.setEmail(user.getEmail());
        userEntity.setIdDocument(user.getIdDocument());
        userEntity.setName(user.getName());
        userEntity.setLastName(user.getLastName());
        userEntity.setPhoneNumber(user.getPhoneNumber());
        userEntity.setBirthDate(user.getBirthDate());
        userEntity.setPassword(user.getPassword());
    }

    @Test
    void testRegister() {
        when(userMapper.toUserEntity(user)).thenReturn(userEntity);
        when(userRepository.save(userEntity)).thenReturn(userEntity);
        when(userMapper.toUser(userEntity)).thenReturn(user);

        // Act
        String result = userJpaAdapter.register(user);

        // Assert
        assertNotNull(result, "The result should not be null");
        assertEquals(user, result);
    }

    @Test
    void testGetUserByIdDocument_UserFound() {
        String idDocument = "123456";

        when(userRepository.findByIdDocument(idDocument)).thenReturn(Optional.of(userEntity));
        when(userMapper.toUser(userEntity)).thenReturn(user);

        // Act
        User result = userJpaAdapter.getUserByIdDocument(idDocument);

        // Assert
        assertNotNull(result, "The result should not be null");
        assertEquals(user, result);
    }

    @Test
    void testGetUserByIdDocument_UserNotFound() {
        String idDocument = "123456";

        when(userRepository.findByIdDocument(idDocument)).thenReturn(Optional.empty());

        // Act
        User result = userJpaAdapter.getUserByIdDocument(idDocument);

        // Assert
        assertEquals(null, result);
    }

    @Test
    void testGetUserByEmail_UserFound() {
        String email = "john.doe@example.com";

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(userEntity));
        when(userMapper.toUser(userEntity)).thenReturn(user);

        // Act
        User result = userJpaAdapter.getUserByEmail(email);

        // Assert
        assertNotNull(result, "The result should not be null");
        assertEquals(user, result);
    }

    @Test
    void testGetUserByEmail_UserNotFound() {
        String email = "john.doe@example.com";

        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        // Act
        User result = userJpaAdapter.getUserByEmail(email);

        // Assert
        assertEquals(null, result);
    }
}
