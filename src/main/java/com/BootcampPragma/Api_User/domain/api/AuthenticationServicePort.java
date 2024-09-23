package com.BootcampPragma.Api_User.domain.api;

import com.BootcampPragma.Api_User.domain.model.Authentication;
import com.BootcampPragma.Api_User.domain.model.User;


public interface AuthenticationServicePort {

    Authentication register(User user);

    Authentication login(Authentication user);

}
