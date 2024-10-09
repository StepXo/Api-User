package com.BootcampPragma.Api_User.domain.spi;

import com.BootcampPragma.Api_User.domain.model.Authentication;
import com.BootcampPragma.Api_User.domain.model.User;

public interface AuthenticationRepositoryPort {

    String authenticate(Authentication user);

}
