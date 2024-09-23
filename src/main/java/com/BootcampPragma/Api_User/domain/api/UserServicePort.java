package com.BootcampPragma.Api_User.domain.api;

import com.BootcampPragma.Api_User.domain.model.Authentication;
import com.BootcampPragma.Api_User.domain.model.User;


public interface UserServicePort {
    User getUserByIdDocument(String id);

    void updateUser(User user);

    void deleteUser(String token);
}
