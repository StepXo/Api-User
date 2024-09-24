package com.BootcampPragma.Api_User.application.handler;


import com.BootcampPragma.Api_User.application.dto.UserResponse;
import com.BootcampPragma.Api_User.application.mapper.UserHandlerMapper;
import com.BootcampPragma.Api_User.domain.api.UserServicePort;
import com.BootcampPragma.Api_User.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
@RequiredArgsConstructor
@Transactional
public class UserHandler {
    private  final UserServicePort userServicePort;
    private final UserHandlerMapper userHandlerMapper;

    public UserResponse getUserByIdDocument(String id) {
        User user = userServicePort.getUserByIdDocument(id);

        return userHandlerMapper.toUserResponseDto(user);
    }
}
