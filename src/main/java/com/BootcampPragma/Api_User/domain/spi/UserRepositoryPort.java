package com.BootcampPragma.Api_User.domain.spi;

import com.BootcampPragma.Api_User.domain.model.User;

import java.util.List;

public interface UserRepositoryPort {

    User getUserByIdDocument(String id);

    User getUserByEmail(String email);

    User register(User user);

    void updateUser(User user);

    void deleteUser(String token);
}
