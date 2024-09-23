package com.BootcampPragma.Api_User.domain.usecase;

import com.BootcampPragma.Api_User.domain.api.AuthenticationServicePort;
import com.BootcampPragma.Api_User.domain.api.UserServicePort;
import com.BootcampPragma.Api_User.domain.exeption.UserEmailAlreadyExistsException;
import com.BootcampPragma.Api_User.domain.exeption.UserIdAlreadyExistsException;
import com.BootcampPragma.Api_User.domain.model.Authentication;
import com.BootcampPragma.Api_User.domain.model.User;
import com.BootcampPragma.Api_User.domain.spi.AuthenticationRepositoryPort;
import com.BootcampPragma.Api_User.domain.spi.UserRepositoryPort;
import com.BootcampPragma.Api_User.domain.utils.Validation;


public class AuthenticationUseCase implements AuthenticationServicePort {

    private final UserRepositoryPort userRepositoryPort;
    private final AuthenticationRepositoryPort authenticationRepositoryPort;


    public AuthenticationUseCase(UserRepositoryPort userRepositoryPort, AuthenticationRepositoryPort authenticationRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
        this.authenticationRepositoryPort = authenticationRepositoryPort;
    }


    @Override
    public Authentication login(Authentication user) {
        //corregir exepciones
        if (user == null || user.getEmail() == null || user.getPassword() == null) {
            throw new IllegalArgumentException("User or password is null");
        }
        User userFromDatabase = userRepositoryPort.getUserByEmail(user.getEmail());
        if (userFromDatabase == null) {
            throw new IllegalArgumentException("User not found");
        }
        user.setToken( authenticationRepositoryPort.authenticate(user) );
        return user;

    }

}
