package com.BootcampPragma.Api_User.application.mapper;

import com.BootcampPragma.Api_User.application.dto.AuthenticationResponse;
import com.BootcampPragma.Api_User.domain.model.Authentication;
import org.mapstruct.Mapper;

import static com.BootcampPragma.Api_User.application.Utils.AppConstants.SPRING;

@Mapper(componentModel = SPRING)
public interface AuthenticationMapper {

    AuthenticationResponse toAuthenticationResponse(Authentication authentication);

}
