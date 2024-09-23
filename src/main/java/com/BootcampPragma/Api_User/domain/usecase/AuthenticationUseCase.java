package com.BootcampPragma.Api_User.domain.usecase;

import com.BootcampPragma.Api_User.domain.api.AuthenticationServicePort;
import com.BootcampPragma.Api_User.domain.exeption.*;
import com.BootcampPragma.Api_User.domain.model.Authentication;
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
        Validation.validate(user);

        if (user.getPassword() == null) {
            throw new PasswordFormatException();
        }
        if (userRepositoryPort.getUserByEmail(user.getEmail()) == null) {
            throw new UserNotFound();
        }

        user.setToken( authenticationRepositoryPort.authenticate(user) );
        return user;

    }

}
