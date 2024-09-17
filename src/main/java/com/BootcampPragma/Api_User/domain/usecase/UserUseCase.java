package com.BootcampPragma.Api_User.domain.usecase;

import com.BootcampPragma.Api_User.domain.api.UserServicePort;
import com.BootcampPragma.Api_User.domain.exeption.UserEmailAlreadyExistsException;
import com.BootcampPragma.Api_User.domain.exeption.UserIdAlreadyExistsException;
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

        Validation.validate(user);
        if (userRepositoryPort.getUserByEmail(user.getEmail()) != null) {
            throw new UserEmailAlreadyExistsException();
        }

        if (userRepositoryPort.getUserByIdDocument(user.getIdDocument()) != null) {
            throw new UserIdAlreadyExistsException();
        }

        return userRepositoryPort.register(user);
    }

    @Override
    public User getUserByIdDocument(String id) {
        return userRepositoryPort.getUserByIdDocument(id);
    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public void deleteUser(String token) {

    }
}
