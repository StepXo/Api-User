package com.BootcampPragma.Api_User.infrastructure.adapters.JpaAdapter;

import com.BootcampPragma.Api_User.domain.model.User;
import com.BootcampPragma.Api_User.domain.spi.UserRepositoryPort;
import com.BootcampPragma.Api_User.infrastructure.adapters.persistance.entity.UserEntity;
import com.BootcampPragma.Api_User.infrastructure.adapters.persistance.mapper.UserMapper;
import com.BootcampPragma.Api_User.infrastructure.adapters.persistance.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserJpaAdapter implements UserRepositoryPort {
    private final UserRepository userRepository;
    private final UserMapper userMapper;


    @Override
    public User register(User user) {
        UserEntity userEntity = this.userRepository.save(userMapper.toUserEntity(user));
        return userMapper.toUser(userEntity);
    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public void deleteUser(String token) {

    }
}
