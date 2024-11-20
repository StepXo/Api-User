package com.BootcampPragma.Api_User.domain.spi;

import com.BootcampPragma.Api_User.domain.model.Authentication;

public interface AuthenticationRepositoryPort {

    String authenticate(Authentication user);

}
