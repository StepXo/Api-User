package com.BootcampPragma.Api_User.domain.usecase;

import com.BootcampPragma.Api_User.domain.api.UserServicePort;
import com.BootcampPragma.Api_User.domain.model.RoleEnum;
import com.BootcampPragma.Api_User.domain.model.User;
import com.BootcampPragma.Api_User.domain.spi.UserRepositoryPort;
import com.BootcampPragma.Api_User.domain.utils.Validation;


public class UserUseCase implements UserServicePort {

    private final UserRepositoryPort userRepositoryPort;


    public UserUseCase(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }



    @Override
    public User getUserById(String id) {
        return userRepositoryPort.getUserById(Long.parseLong(id));
    }

    @Override
    public String setRole(long id, String role) {
        User user = userRepositoryPort.getUserById(id);
        Validation.validate(user, role);
        user.setRole(RoleEnum.valueOf(role));
        userRepositoryPort.updateUser(user);
        return role;
    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public void deleteUser(String token) {

    }
}
