package com.BootcampPragma.Api_User.infrastructure.adapters.persistance.mapper;

import com.BootcampPragma.Api_User.domain.model.User;
import com.BootcampPragma.Api_User.infrastructure.Utils.InfraConstants;
import com.BootcampPragma.Api_User.infrastructure.adapters.persistance.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = InfraConstants.SPRING)
public interface UserMapper {

    UserEntity toUserEntity(User user);

    User toUser(UserEntity userEntity);

}

