package com.BootcampPragma.Api_User.application.mapper;

import com.BootcampPragma.Api_User.application.dto.UserResponse;
import com.BootcampPragma.Api_User.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserHandlerMapper {
    UserResponse toUserResponseDto(User user);

}
