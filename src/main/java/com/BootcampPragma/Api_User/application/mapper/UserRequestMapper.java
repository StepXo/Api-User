package com.BootcampPragma.Api_User.application.mapper;

import com.BootcampPragma.Api_User.application.dto.UserRequestDto;
import com.BootcampPragma.Api_User.domain.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserRequestMapper {
    User toUser(UserRequestDto userRequestDto);
}
