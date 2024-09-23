package com.BootcampPragma.Api_User.application.mapper;

import com.BootcampPragma.Api_User.application.dto.AuthenticationResponse;
import com.BootcampPragma.Api_User.domain.model.Authentication;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthenticationMapper {

    AuthenticationResponse toAuthenticationResponse(Authentication authentication);

}
