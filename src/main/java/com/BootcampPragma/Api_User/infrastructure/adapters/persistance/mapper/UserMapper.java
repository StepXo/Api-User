package com.BootcampPragma.Api_User.infrastructure.adapters.persistance.mapper;

import com.BootcampPragma.Api_User.domain.model.User;
import com.BootcampPragma.Api_User.infrastructure.adapters.persistance.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserEntity toUserEntity(User user);
    User toUser(UserEntity userEntity);

}
