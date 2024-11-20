package com.BootcampPragma.Api_User.infrastructure.adapters.JpaAdapter;

import com.BootcampPragma.Api_User.domain.model.User;
import com.BootcampPragma.Api_User.domain.spi.UserRepositoryPort;
import com.BootcampPragma.Api_User.infrastructure.adapters.persistance.entity.UserEntity;
import com.BootcampPragma.Api_User.infrastructure.adapters.persistance.mapper.UserMapper;
import com.BootcampPragma.Api_User.infrastructure.adapters.persistance.repository.UserRepository;
import com.BootcampPragma.Api_User.infrastructure.adapters.securityconfig.jwtconfiguration.JwtService;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class UserJpaAdapter implements UserRepositoryPort {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final JwtService jwtService;


    @Override
    public String register(User user) {

        UserEntity userEntity = userRepository.save(userMapper.toUserEntity(user));

        return jwtService.getToken(userEntity);
    }

    public User getUserByIdDocument(String id) {
        return userRepository.findByIdDocument(id)
                .map(userMapper::toUser)
                .orElse(null);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(userMapper::toUser)
                .orElse(null);
    }

    @Override
    public User getUserById(long id) {
        return userRepository.findById(id)
                .map(userMapper::toUser)
                .orElse(null);
    }


    @Override
    public void updateUser(User user) {

    }

    @Override
    public void deleteUser(String token) {

    }
}
