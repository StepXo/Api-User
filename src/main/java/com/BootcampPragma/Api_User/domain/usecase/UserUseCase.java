package com.BootcampPragma.Api_User.domain.usecase;

import com.BootcampPragma.Api_User.domain.api.UserServicePort;
import com.BootcampPragma.Api_User.domain.model.User;
import com.BootcampPragma.Api_User.domain.spi.UserRepositoryPort;
import com.BootcampPragma.Api_User.domain.utils.Validation;


public class UserUseCase implements UserServicePort {

    private final UserRepositoryPort userRepositoryPort;


    public UserUseCase(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    public User register(User user) {
        Validation.validateEmail(user.getEmail());
        Validation.validatePhoneNumber(user.getPhoneNumber());
        Validation.validateIdDocument(user.getIdDocument());
        Validation.validateAge(user.getBirthDate());
        return userRepositoryPort.register(user);
    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public void deleteUser(String token) {

    }
}
