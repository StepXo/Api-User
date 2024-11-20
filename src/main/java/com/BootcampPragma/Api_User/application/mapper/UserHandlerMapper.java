package com.BootcampPragma.Api_User.application.mapper;

import com.BootcampPragma.Api_User.application.dto.UserResponse;
import com.BootcampPragma.Api_User.domain.model.User;
import org.mapstruct.Mapper;

import static com.BootcampPragma.Api_User.application.Utils.AppConstants.SPRING;

@Mapper(componentModel = SPRING)
public interface UserHandlerMapper {
    UserResponse toUserResponseDto(User user);

}
