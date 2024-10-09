package com.BootcampPragma.Api_User.domain.spi;

import com.BootcampPragma.Api_User.domain.model.User;

import java.util.List;

public interface UserRepositoryPort {

    User getUserByEmail(String email);
    User getUserById(long id);

    String register(User user);

    void updateUser(User user);

    void deleteUser(String token);
}
