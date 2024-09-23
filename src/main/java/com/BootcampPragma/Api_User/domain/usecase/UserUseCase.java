package com.BootcampPragma.Api_User.domain.usecase;

import com.BootcampPragma.Api_User.domain.api.UserServicePort;
import com.BootcampPragma.Api_User.domain.exeption.UserEmailAlreadyExistsException;
import com.BootcampPragma.Api_User.domain.exeption.UserIdAlreadyExistsException;
import com.BootcampPragma.Api_User.domain.model.Authentication;
import com.BootcampPragma.Api_User.domain.model.User;
import com.BootcampPragma.Api_User.domain.spi.UserRepositoryPort;
import com.BootcampPragma.Api_User.domain.utils.Validation;


public class UserUseCase implements UserServicePort {

    private final UserRepositoryPort userRepositoryPort;


    public UserUseCase(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
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
